package com.m2i.rest.movie.dao;

import com.m2i.rest.movie.model.Comment;
import com.m2i.rest.movie.model.Genre;
import com.m2i.rest.movie.model.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class MovieDao extends AbstractDao<Movie> {

    public MovieDao() {
        setClazz(Movie.class);
    }

    public void addComment(int movieId, Comment comment) throws Exception {
        Movie movie = findById(movieId);
        
        if (movie.getComments() == null) {
            movie.setComments(new ArrayList<>());
        }
        
        comment.setMovie(movie);
        movie.getComments().add(comment);
        
        update(movieId, movie);
    }
    
    public List<Movie> getByGenre(Genre genre) {
        
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query findByGenreQuery = entityManager.createQuery("select m from Movie m join m.genres g where g.id = :genreId");
        
        findByGenreQuery.setParameter("genreId", genre.getId());
        
        return findByGenreQuery.getResultList();
        
    }
   
}
