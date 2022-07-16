package com.m2i.rest.movie.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SessionHelper {

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_db");
            entityManager = emf.createEntityManager();
        }

        return entityManager;
    }
}
