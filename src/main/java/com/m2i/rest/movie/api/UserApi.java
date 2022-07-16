package com.m2i.rest.movie.api;

import com.m2i.rest.movie.dao.UserDao;
import com.m2i.rest.movie.model.User;
import javax.ws.rs.Path;

@Path("/users")
public class UserApi extends AbstractApi<User, UserDao> {

   
}
