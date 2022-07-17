package com.m2i.rest.movie.dao;

import com.m2i.rest.movie.model.AbstractEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

public abstract class AbstractDao<T extends AbstractEntity<T>> {

    private Class<T> clazz;

    public final void setClazz(final Class<T> clazzToSet) {
        clazz = clazzToSet;
    }
    
    public List<T> findAll() {

        EntityManager entityManager = SessionHelper.getEntityManager();
        Query findAllQuery = entityManager.createQuery("from " + clazz.getName());

        return findAllQuery.getResultList();
    }

    public T findById(int id) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        T entityFound = entityManager.find(clazz, id);

        return entityFound;
    }
    

    public void save(T entityToCreate) {
        // On vérifie les données que l'on reçoit en paramètre
        if (entityToCreate == null) {
            System.out.println("L'entité ne peut pas être null");
            return;
        }

        EntityManager entityManager = SessionHelper.getEntityManager();

        // On déclare notre transaction avec pour valeur par défaut null
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.merge(entityToCreate);

            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la création");
            if (tx != null) {
                // Une erreur est survenue, on discard les actions entamés dans la transaction
                tx.rollback();
            }
        }
    }

    public void update(int id, T entity) throws Exception {
        EntityManager entityManager = SessionHelper.getEntityManager();
        // On récupère le utilisateur qu'on souhaite modifier
        T entityToUpdate = entityManager.find(clazz, id);

        // Si le utilisateur n'existe pas on ne fait pas d'update
        if (entityToUpdate == null) {
            throw new NotFoundException("L'entité avec l'id:" + id + " n'existe pas");
        }

        entityToUpdate.copy(entity);
        
        save(entityToUpdate);
    }

    public void delete(int id) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        // On récupère le utilisateur qu'on souhaite supprimer
        T entityToDelete = entityManager.find(clazz, id);

        if (entityToDelete == null) {
            throw new NotFoundException("L'entité avec l'id:" + id + " n'existe pas");
        }

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.remove(entityToDelete);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la suppresion");
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

}
