package com.m2i.rest.movie.api;

import com.m2i.rest.movie.dao.ActorDao;
import com.m2i.rest.movie.dao.CommentDao;
import com.m2i.rest.movie.dao.GenreDao;
import com.m2i.rest.movie.dao.MovieDao;
import com.m2i.rest.movie.model.Actor;
import com.m2i.rest.movie.model.Comment;
import com.m2i.rest.movie.model.Genre;
import com.m2i.rest.movie.model.Movie;
import com.m2i.rest.movie.model.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
    
    @Path("/getByGenre")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Movie> getByGenre(@Context HttpServletRequest request) {
        MovieDao dao = new MovieDao();
        GenreDao genreDao = new GenreDao();
        String genreName = request.getParameter("q");
        
        if (genreName == null) {
            throw new WebApplicationException("You must provide the query param 'q'", Response.Status.BAD_REQUEST);
        }
        
        List<Genre> genreResults = genreDao.findByName(genreName, 1);
        
        if (genreResults.isEmpty()) {
            throw new WebApplicationException("No genre found", Response.Status.NOT_FOUND);
        }
        
        return dao.getByGenre(genreResults.get(0));
    }
    
    @Path("/getByActor")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Movie> getByActor(@Context HttpServletRequest request) {
        ActorDao actorDao = new ActorDao();
        String actorName = request.getParameter("q");
        
        if (actorName == null) {
            throw new WebApplicationException("You must provide the query param 'q'", Response.Status.BAD_REQUEST);
        }
        
        List<Actor> actorResults = actorDao.findByFirstnameOrLastname(actorName, 1);
        
        if (actorResults.isEmpty()) {
            throw new WebApplicationException("No actors found", Response.Status.NOT_FOUND);
        }
        
        return actorResults.get(0).getMovies();
    }
}
