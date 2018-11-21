package bases;

import models.ResponseApiModel;

import javax.ws.rs.core.Response;
import java.util.List;

public class EndPointBase <T> {

    private ResponseApiModel<T> response = new ResponseApiModel();

    protected Response generateResponse(boolean success){
        response = new ResponseApiModel();
        response.setSuccess(success);
        if (!success) response.setReason("Error db");
        return Response.status(Response.Status.OK).entity(response).build();
    }

    protected Response generateResponse(String reason){
        response = new ResponseApiModel();
        response.setReason(reason);
        response.setSuccess(false);
        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }

    protected  Response generateReponse(T item){
        response = new ResponseApiModel<>();
        response.setSuccess(true);
        response.setItem(item);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    protected Response generateReponse(List<T> items){
        response = new ResponseApiModel<>();
        response.setSuccess(true);
        response.setItems(items);
        return Response.status(Response.Status.OK).entity(response).build();
    }

}
