package com.m2i.rest.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class UtilisateurDao {

    public List<Utilisateur> findAll(){
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query findAllQuery = entityManager.createQuery("select u from Utilisateur u");
        return findAllQuery.getResultList();
    }
}
