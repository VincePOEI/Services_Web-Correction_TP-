package com.m2i.rest.movie.dao;

import com.m2i.rest.movie.model.Genre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class GenreDao extends AbstractDao<Genre> {

    public GenreDao() {
        setClazz(Genre.class);
    }

    public List<Genre> findByName(String name, int maxResults) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query findByNameQuery = entityManager.createQuery("select g from Genre g where g.name = :name");
        
        findByNameQuery.setParameter("name", name);
        findByNameQuery.setMaxResults(maxResults);
        
        return findByNameQuery.getResultList();
    }
    
    
}
