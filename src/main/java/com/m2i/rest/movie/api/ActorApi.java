package com.m2i.rest.movie.api;

import com.m2i.rest.movie.dao.ActorDao;
import com.m2i.rest.movie.model.Actor;
import javax.ws.rs.Path;

@Path("/actors")
public class ActorApi extends AbstractApi<Actor, ActorDao> {

   
}
