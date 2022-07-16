package com.m2i.rest.movie.dao;

import com.m2i.rest.movie.model.Comment;
import com.m2i.rest.movie.model.Movie;
import java.util.ArrayList;


public class MovieDao extends AbstractDao<Movie> {

    public MovieDao() {
        setClazz(Movie.class);
    }

    public void addComment(int movieId, Comment comment) throws Exception {
        Movie movie = findById(movieId);
        
        if (movie.getComments() == null) {
            movie.setComments(new ArrayList<>());
        }
        
        movie.getComments().add(comment);
        
        update(movieId, movie);
    }
   
}
