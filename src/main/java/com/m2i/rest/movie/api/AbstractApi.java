package com.m2i.rest.movie.api;

import com.m2i.rest.movie.dao.AbstractDao;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class AbstractApi<TEntity, TDao extends AbstractDao<TEntity>> {

    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public List<TEntity> getList(@Context HttpServletRequest request) {

        TDao dao = getInstanceOfDao();

        return dao.findAll();
    }

    @POST()
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public TEntity createOne(TEntity entity, @Context HttpServletRequest request) {

        TDao dao = getInstanceOfDao();

        dao.create(entity);

        return entity;
    }

    @Path("/{id}")
    @PUT()
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public TEntity updateOne(@PathParam("id") int id, TEntity entity, @Context HttpServletRequest request) {
        TDao dao = getInstanceOfDao();

        try {
            dao.update(id, entity);

        } catch (NotFoundException e) {
            throw new WebApplicationException("Entity was not found", Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new WebApplicationException("An error occured", Response.Status.BAD_REQUEST);
        }
            
        return entity;
    }
    
    @Path("/{id}")
    @DELETE()
    public void delete(@PathParam("id") int id, @Context HttpServletRequest request){
       TDao dao = getInstanceOfDao();
       
       try {
            dao.delete(id);

        } catch (NotFoundException e) {
            throw new WebApplicationException("Entity was not found", Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new WebApplicationException("An error occured", Response.Status.BAD_REQUEST);
        }
          
    }
    
    @Path("/{id}")
    @GET()
    @Produces({ MediaType.APPLICATION_JSON })
    public TEntity getOne(@PathParam("id") int id, @Context HttpServletRequest request) {
        
         TDao dao = getInstanceOfDao();
         TEntity entity = dao.findById(id);
         
         if (entity == null) {
             throw new WebApplicationException("Entity was not found", Response.Status.NOT_FOUND);
         }
         
         return entity;
    }
    
    private TDao getInstanceOfDao()
    {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<TDao> type = (Class<TDao>) superClass.getActualTypeArguments()[1];
        try
        {
            return type.newInstance();
        }
        catch (Exception e)
        {
            // Oops, no default constructor
            throw new RuntimeException(e);
        }
    }
}
