package interfaces;

import java.util.List;

public interface RepositoryApi <T> {
    boolean insertItem(T item);
    boolean deleteItem(int id);
    boolean updateItem(T item);
    List<T> getAllItems();
    T getItemById(int id);
    List<T> getWhereItems(int max, String where, String order);
}
