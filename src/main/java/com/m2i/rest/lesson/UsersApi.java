package com.m2i.rest.lesson;


import com.m2i.rest.data.Utilisateur;
import com.m2i.rest.data.UtilisateurDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UsersApi {
    
    @GET()
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Utilisateur> getUsers(@Context HttpServletRequest request) {
        
        UtilisateurDao dao = new UtilisateurDao();
        
        return dao.findAll();
    }
    
    @POST()
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Utilisateur postUsers(Utilisateur newUtilisateur, @Context HttpServletRequest request) {
        
        UtilisateurDao dao = new UtilisateurDao();
        
        dao.create(newUtilisateur);
        
        return newUtilisateur;        
    }
    
}