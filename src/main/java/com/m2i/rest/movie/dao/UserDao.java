package com.m2i.rest.movie.dao;

import com.m2i.rest.movie.model.User;


public class UserDao extends AbstractDao<User> {

    public UserDao() {
        setClazz(User.class);
    }
    
}
