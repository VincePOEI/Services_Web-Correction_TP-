package com.m2i.rest.movie.dao;

import com.m2i.rest.movie.model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class UserDao extends AbstractDao<User> {

    public UserDao() {
        setClazz(User.class);
    }
    
    public List<User> findByEmail(String email) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query findByEmailQuery = entityManager.createQuery("select u from User u where u.email = :email");
        
        findByEmailQuery.setParameter("email", email);
        findByEmailQuery.setMaxResults(1);
        
        return findByEmailQuery.getResultList();
    }
    
}
