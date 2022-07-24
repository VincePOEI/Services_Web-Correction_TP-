package com.m2i.rest.lesson;

import com.m2i.rest.data.User;
import com.m2i.rest.data.UserDao;
import java.util.List;
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
    public List<User> getUsers() {
        UserDao dao = new UserDao();
        return dao.findAll();
    }

    @POST()
    @Consumes({MediaType.APPLICATION_JSON})
    public Response postUser(User newUtilisateur) {
        UserDao dao = new UserDao();

        try {
            dao.create(newUtilisateur);
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }

        return Response.status(Response.Status.CREATED)
                .entity("User successfully created")
                .build();
    }

    @Path("/{id}")
    @PUT()
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response putUser(@PathParam("id") int id, User utilisateur) {
        UserDao dao = new UserDao();

        try {
            dao.update(id, utilisateur);

        } catch (NotFoundException e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("User was not found")
                            .build()
            );
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }

        return Response.status(Response.Status.OK).entity("User successfully modified").build();
    }

    @Path("/{id}")
    @DELETE()
    public Response deleteUser(@PathParam("id") int userId) {
        UserDao dao = new UserDao();

        try {
            dao.delete(userId);
        } catch (NotFoundException e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("User was not found")
                            .build()
            );
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }
        
        return Response.status(Response.Status.OK).entity("User successfully deleted").build();
    }

    @Path("/{id}")
    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public User getUser(@PathParam("id") int userId, @Context HttpServletRequest request) {

        UserDao dao = new UserDao();
        User user = dao.findById(userId);

        if (user == null) {
            throw new WebApplicationException("User was not found", Response.Status.NOT_FOUND);
        }

        return user;
    }

    @Path("/search")
    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> searchUser(@Context HttpServletRequest request) {

        String queryParam = request.getParameter("q");
        String countParam = request.getParameter("count");
        int count = 1;

        if (queryParam == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }

        try {
            count = Integer.parseInt(countParam);
        } catch (NumberFormatException e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }

        UserDao dao = new UserDao();
        List<User> searchResult = dao.search(queryParam, count);

        if (searchResult == null || searchResult.isEmpty()) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("No user found")
                            .build()
            );
        }

        return searchResult;
    }

}
