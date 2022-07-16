package com.m2i.rest.movie.api;

import com.m2i.rest.movie.dao.MovieDao;
import com.m2i.rest.movie.model.Movie;
import javax.ws.rs.Path;

@Path("/movies")
public class MovieApi extends AbstractApi<Movie, MovieDao> {

   
}
