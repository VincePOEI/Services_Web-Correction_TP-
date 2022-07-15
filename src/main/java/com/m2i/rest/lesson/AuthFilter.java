package com.m2i.rest.lesson;

import com.m2i.rest.data.Utilisateur;
import java.io.IOException;
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
        String path = containerRequest.getUriInfo().getPath(true);
        
        if ("GET".equals(method) && "personnes".equals(path)) {
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
 
        Utilisateur authentificationResult = checkUser(lap[0], lap[1]);
 
        if(authentificationResult == null){
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
 
        request.setAttribute("user", authentificationResult);     
    }
    
    public Utilisateur checkUser(String email, String password) {
        if (email.equals("admin@admin.com")) {
            return new Utilisateur();
        }
        
        return null;
    }
    
}
