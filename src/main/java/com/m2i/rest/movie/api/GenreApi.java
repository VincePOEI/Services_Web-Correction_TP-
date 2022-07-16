package com.m2i.rest.movie.api;

import com.m2i.rest.movie.dao.GenreDao;
import com.m2i.rest.movie.model.Genre;
import javax.ws.rs.Path;

@Path("/genres")
public class GenreApi extends AbstractApi<Genre, GenreDao> {

   
}
