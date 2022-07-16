package com.m2i.rest.movie.api;

import com.m2i.rest.movie.dao.MovieDao;
import com.m2i.rest.movie.model.Comment;
import com.m2i.rest.movie.model.Movie;
import com.m2i.rest.movie.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/movies")
public class MovieApi extends AbstractApi<Movie, MovieDao> {

    @Path("/{id}/comments")
    @POST()
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Comment addCommentToMovie(@PathParam("id") int movieId, Comment newComment, @Context HttpServletRequest request) {
        MovieDao dao = new MovieDao();
        User user = (User) request.getAttribute("user");

        newComment.setUser(user);

        try {
            dao.addComment(movieId, newComment);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return newComment;
    }
}
