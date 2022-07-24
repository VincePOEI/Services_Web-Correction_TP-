package com.m2i.rest.lesson;

import com.m2i.rest.data.User;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class AuthFilter implements ContainerRequestFilter {

    @Context
    public HttpServletRequest request;
    
    
    @Override
    public void filter(ContainerRequestContext containerRequest) throws IOException {
        // Get the authentification passed in HTTP headers parameters
        String auth = containerRequest.getHeaderString("Authorization");
        
        // If the user does not have the right (does not provide any HTTP Basic Auth)
        if (auth == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You must be connected")
                            .build()
            );
        }

        // lap : loginAndPassword
        String[] lap = BasicAuth.decode(auth);
 
        if (lap == null || lap.length != 2) {
            throw new WebApplicationException(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You must be connected")
                            .build()
            );
        }

        User authentificationResult = checkUser(lap[0], lap[1]);
 
        if (authentificationResult == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You must be connected")
                            .build()
            );
        }
 
        request.setAttribute("user", authentificationResult);     
    }

    public User checkUser(String email, String password) {
        return "admin@admin.com".equals(email) && "admin".equals(password) ?
                new User("Super", "User", "admin", "admin@admin.com", "admin") :
                null;
    }
}
