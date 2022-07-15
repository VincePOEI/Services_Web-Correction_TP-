package com.m2i.rest.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


public class UtilisateurDao {

    public List<Utilisateur> findAll(){
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query findAllQuery = entityManager.createQuery("select u from Utilisateur u");
        return findAllQuery.getResultList();
    }
    
    public void create(Utilisateur utilisateurToCreate) {
        // On vérifie les données que l'on reçoit en paramètre
        if (utilisateurToCreate == null) {
            System.out.println("L'objet utilisateur ne peut pas être null");
            return;
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
        }
    }
}
