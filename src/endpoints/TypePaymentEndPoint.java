package endpoints;

import bases.EndPointBase;
import entities.TypePayment;
import interfaces.EndPointApi;
import repositories.TypePaymentRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/type-pyment")
public class TypePaymentEndPoint extends EndPointBase<TypePayment> implements EndPointApi<TypePayment> {

    @Inject
    private TypePaymentRepository services;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insert")
    @Override
    public Response newItem(TypePayment item) {
        try {
            return generateResponse(services.insertItem(item));
        } catch (Exception ex) {
            return generateResponse(ex.getMessage());
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    @Override
    public Response updateItem(TypePayment item) {
        try{
            return generateResponse(services.updateItem(item));
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
            return generateResponse(services.deleteItem(id));
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
            return generateReponse(services.getWhereItems(max,where,orderBy));
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
            return generateReponse(services.getAllItems());
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
            return generateReponse(services.getItemById(id));
        }catch (Exception ex){
            return generateResponse(ex.getMessage());
        }
    }
}
