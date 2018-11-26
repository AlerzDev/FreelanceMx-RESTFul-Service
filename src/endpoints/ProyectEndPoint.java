package endpoints;

import bases.EndPointBase;
import com.google.common.primitives.Ints;
import entities.Project;
import entities.UserProject;
import interfaces.EndPointApi;
import repositories.ProjectRepository;
import repositories.UserProjectRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/project")
public class ProyectEndPoint extends EndPointBase<Project> implements EndPointApi<Project> {

    @Inject
    private ProjectRepository services;

    @Inject
    UserProjectRepository servicesUserProject;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Response newItem(@QueryParam("idUser") int id, Project item) {
        try {
            long idProject = services.insertItemGetId(item);
            UserProject i = new UserProject();
            i.setIdProject(idProject);
            i.setIdUser(id);
            return generateResponse(servicesUserProject.insertItem(i));
        } catch (Exception ex) {
            return generateResponse(ex.getMessage());
        }
    }

    @Override
    public Response newItem(Project item) {
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
    public Response updateItem(Project item) {
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

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/user-project/{id}")
    public Response getUserProject(@PathParam("id")int id) {
        try{
            return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").entity(servicesUserProject.getUserProject(id)).build();
        }catch (Exception ex){
            return generateResponse(ex.getMessage());
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/get-projects-user/{id}")
    public Response getProjectForUSer(@PathParam("id")int id) {
        try{
            List<UserProject> userProjects = servicesUserProject.getWhereItems(30,"a.idUser="+id,null);
            List<Project> projects = new ArrayList<>();
            for (UserProject u:userProjects) {
                projects.add(services.getItemById(u.getIdProject()));
            }
            return generateReponse(projects);
        }catch (Exception ex){
            return generateResponse(ex.getMessage());
        }
    }

}
