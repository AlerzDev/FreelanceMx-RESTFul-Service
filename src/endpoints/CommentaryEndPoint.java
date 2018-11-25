package endpoints;

import bases.EndPointBase;
import entities.Commentary;
import interfaces.EndPointApi;
import repositories.CommentaryRepository;
import repositories.CommentaryUserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CommentaryEndPoint extends EndPointBase<Commentary> implements EndPointApi<Commentary> {

    @Inject
    private CommentaryRepository servicesCommentary;

    @Inject
    private CommentaryUserRepository servicesCommentaryUser;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insert")
    @Override
    public Response newItem(Commentary item) {
        try {
            return generateResponse(servicesCommentary.insertItem(item));
        } catch (Exception ex) {
            return generateResponse(ex.getMessage());
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    @Override
    public Response updateItem(Commentary item) {
        try{
            return generateResponse(servicesCommentary.updateItem(item));
        }catch (Exception ex) {
            return generateResponse(ex.getMessage());
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    @Override
    public Response deleteItem(@PathParam("id") int id) {
        try {
            return generateResponse(servicesCommentary.deleteItem(id));
        }catch (Exception ex){
            return generateResponse(ex.getMessage());
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/get-where")
    @Override
    public Response getItems(@QueryParam("max") int max ,@QueryParam("where") String where, @QueryParam("orderBy") String orderBy) {
        try{
            return generateReponse(servicesCommentary.getWhereItems(max,where,orderBy));
        }catch (Exception ex){
            return generateResponse(ex.getMessage());
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/get-all")
    @Override
    public Response getAllItems() {
        try{
            return generateReponse(servicesCommentary.getAllItems());
        }catch (Exception ex){
            return generateResponse(ex.getMessage());
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/get-by-id/{id}")
    @Override
    public Response getItemById(@PathParam("id")int id) {
        try{
            return generateReponse(servicesCommentary.getItemById(id));
        }catch (Exception ex){
            return generateResponse(ex.getMessage());
        }
    }
}
