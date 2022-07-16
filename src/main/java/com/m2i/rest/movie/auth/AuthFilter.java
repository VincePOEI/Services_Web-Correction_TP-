package com.m2i.rest.movie.auth;


import com.m2i.rest.movie.dao.UserDao;
import com.m2i.rest.movie.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class AuthFilter implements ContainerRequestFilter {

    @Context
    public HttpServletRequest request;
    
    
    @Override
    public void filter(ContainerRequestContext containerRequest) throws IOException {
        String method = containerRequest.getMethod();
        
        if ("GET".equals(method)) {
            return;
        }       
        
        //Get the authentification passed in HTTP headers parameters
        String auth = containerRequest.getHeaderString("Authorization");
        
        //If the user does not have the right (does not provide any HTTP Basic Auth)
        if(auth == null){
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
 
        //lap : loginAndPassword
        String[] lap = BasicAuth.decode(auth);
 
        if(lap == null || lap.length != 2){
            throw new WebApplicationException("You must be connected", Status.UNAUTHORIZED);
        }
        
        User authentificationResult = checkUser(lap[0], lap[1]);
 
        if(authentificationResult == null){
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
 
        request.setAttribute("user", authentificationResult);     
    }
   
    public User checkUser(String email, String password) {
        
        UserDao dao = new UserDao();
        
        List<User> users = dao.findByEmail(email);
        
        if (users.isEmpty()) {
            return null;
        }
        
        User user = users.get(0);
        
        if (!user.getPassword().equals(password)) {
            return null;
        }
        
        return user;
    }
    
}
