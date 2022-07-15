package com.m2i.rest.lesson;

import com.m2i.rest.data.SessionHelper;
import com.m2i.rest.data.Utilisateur;
import com.m2i.rest.data.UtilisateurDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UsersApi {

    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public List<Utilisateur> getUsers(@Context HttpServletRequest request) {

        UtilisateurDao dao = new UtilisateurDao();

        return dao.findAll();
    }

    @POST()
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Utilisateur postUsers(Utilisateur newUtilisateur, @Context HttpServletRequest request) {

        UtilisateurDao dao = new UtilisateurDao();

        dao.create(newUtilisateur);

        return newUtilisateur;
    }

    @Path("/{id}")
    @PUT()
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Utilisateur putUser(@PathParam("id") int id, Utilisateur utilisateur, @Context HttpServletRequest request) {
        UtilisateurDao dao = new UtilisateurDao();

        try {
            dao.update(id, utilisateur);

        } catch (NotFoundException e) {
            throw new WebApplicationException("User was not found", Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new WebApplicationException("An error occured", Response.Status.BAD_REQUEST);
        }
            
        return utilisateur;
    }

}
