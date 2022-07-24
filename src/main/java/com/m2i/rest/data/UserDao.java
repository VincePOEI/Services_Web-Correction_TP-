package com.m2i.rest.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

public class UserDao {

    public List<User> findAll() {
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query findAllQuery = entityManager.createQuery("select u from User u");
        return findAllQuery.getResultList();
    }
    
     public User findById(int id) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        User utilisateurFound = entityManager.find(User.class, id);

        if (utilisateurFound == null) {
            System.out.println("Attention le utilisateur avec l'id: " + id + " n'existe pas !");
        }

        return utilisateurFound;
    }

    public void create(User utilisateurToCreate) throws BadRequestException {
        // On vérifie les données que l'on reçoit en paramètre
        if (utilisateurToCreate == null) {
            System.out.println("L'objet utilisateur ne peut pas être null");
            return;
        }
        
        if (utilisateurToCreate.hasAFieldEmpty()) {
            throw new BadRequestException("All the fields must be filled");
        }

        EntityManager entityManager = SessionHelper.getEntityManager();

        // On déclare notre transaction avec pour valeur par défaut null
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.persist(utilisateurToCreate);

            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la création");
            if (tx != null) {
                // Une erreur est survenue, on discard les actions entamés dans la transaction
                tx.rollback();
            }
            throw e;
        }
    }

    public void update(int id, User utilisateurData) throws NotFoundException {
        EntityManager entityManager = SessionHelper.getEntityManager();
        // On récupère le utilisateur qu'on souhaite modifier
        User utilisateurToUpdate = entityManager.find(User.class, id);

        // Si le utilisateur n'existe pas on ne fait pas d'update
        if (utilisateurToUpdate == null) {
            throw new NotFoundException("L'utilisateur avec l'id:" + id + " n'existe pas");
        }

        // on set les données uniquement si elle ne sont pas null
        utilisateurToUpdate.copy(utilisateurData);

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(utilisateurToUpdate);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la modification");
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    public void delete(int id) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        // On récupère le utilisateur qu'on souhaite supprimer
        User utilisateurToDelete = entityManager.find(User.class, id);

        if (utilisateurToDelete == null) {
            throw new NotFoundException("L'utilisateur avec l'id:" + id + " n'existe pas");
        }

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.remove(utilisateurToDelete);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la suppresion");
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
    
    public List<User> search(String query, int count) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query searchQuery = entityManager.createQuery("select u from User u where u.lastname like :query or u.email like :query");

        searchQuery.setParameter("query", "%" + query + "%");
        searchQuery.setMaxResults(count);
        
        return searchQuery.getResultList();
    }
}
