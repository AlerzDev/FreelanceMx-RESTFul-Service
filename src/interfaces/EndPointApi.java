package interfaces;

import javax.ws.rs.core.Response;

public interface EndPointApi <T> {

    Response newItem(T item);

    Response updateItem(T item);

    Response deleteItem(int id);

    Response getItems(int max, String where, String orderBy);

    Response getAllItems();

    Response getItemById(int id);

}
