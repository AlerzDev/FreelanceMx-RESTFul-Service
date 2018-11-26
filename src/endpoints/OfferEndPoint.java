package endpoints;

import bases.EndPointBase;
import entities.Offer;
import entities.OffersProject;
import interfaces.EndPointApi;
import repositories.OfferRepository;
import repositories.OffersProjectRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/offers")
public class OfferEndPoint extends EndPointBase<Offer> implements EndPointApi<Offer> {

    @Inject
    private OfferRepository servicesOffer;

    @Inject
    private OffersProjectRepository servicesOffersProject;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Response newItem(@QueryParam("idProject") int project,Offer item) {
        try {
            long idOffer = servicesOffer.insertItemGetId(item);
            OffersProject i = new OffersProject();
            i.setIdOffer(idOffer);
            i.setIdProject(project);
            return generateResponse(servicesOffersProject.insertItem(i));
        } catch (Exception ex) {
            return generateResponse(ex.getMessage());
        }
    }

    @Override
    public Response newItem(Offer item) {
        return null;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    @Override
    public Response updateItem(Offer item) {
        try{
            return generateResponse(servicesOffer.updateItem(item));
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
            return generateResponse(servicesOffer.deleteItem(id));
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
            return generateReponse(servicesOffer.getWhereItems(max,where,orderBy));
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
            return generateReponse(servicesOffer.getAllItems());
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
            return generateReponse(servicesOffer.getItemById(id));
        }catch (Exception ex){
            return generateResponse(ex.getMessage());
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/get-offer-project")
    public Response getOfferProject(@QueryParam("max") int max ,@QueryParam("where") String where, @QueryParam("orderBy") String orderBy) {
        try{
            return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").entity(servicesOffersProject.getWhereItems(max,where,orderBy)).build();
        }catch (Exception ex){
            return generateResponse(ex.getMessage());
        }
    }

}
