package com.m2i.rest.movie.api;

import com.m2i.rest.movie.dao.RoleDao;
import com.m2i.rest.movie.model.Role;
import javax.ws.rs.Path;

@Path("/roles")
public class RoleApi extends AbstractApi<Role, RoleDao> {

   
}
