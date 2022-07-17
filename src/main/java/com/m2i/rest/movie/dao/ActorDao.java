package com.m2i.rest.movie.dao;

import com.m2i.rest.movie.model.Actor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class ActorDao extends AbstractDao<Actor> {

    public ActorDao() {
        setClazz(Actor.class);
    }

    public List<Actor> findByFirstnameOrLastname(String name, int maxResults) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query findByNameQuery = entityManager.createQuery("select a from Actor a where a.firstname like :name or a.lastname like :name");
        
        findByNameQuery.setParameter("name", "%" + name + "%");
        findByNameQuery.setMaxResults(maxResults);
        
        return findByNameQuery.getResultList();
    }
   
}
