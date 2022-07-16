package com.m2i.rest.movie.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ModelMain {


    public static void main(String args[]) {
       
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie_db");
         EntityManager entityManager = emf.createEntityManager();
         
         
         entityManager.close();
        
    }
}
