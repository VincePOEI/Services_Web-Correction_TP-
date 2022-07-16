package com.m2i.rest.movie.api;

import com.m2i.rest.movie.dao.RoleDao;
import com.m2i.rest.movie.dao.UserDao;
import com.m2i.rest.movie.model.Role;
import com.m2i.rest.movie.model.User;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;


@Provider
public class ApiConfiguration implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
       RoleDao roleDao = new RoleDao();
       UserDao userDao = new UserDao();
        
       Role adminRole = new Role("admin", "Admin user");
       
       roleDao.save(adminRole);
       
       User adminUser = new User("admin", "admin", "BigBoss", "admin@mail.com", "admin", roleDao.findById(1));
       
       userDao.save(adminUser);
        
       return true;
    }

    
}
