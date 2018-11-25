package repositories;

import bases.RepositoryBase;
import entities.CommentaryUser;
import interfaces.RepositoryApi;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CommentaryUserRepository extends RepositoryBase implements RepositoryApi<CommentaryUser> {


    @Override
    public boolean insertItem(CommentaryUser item) {
        try{
            return executeInsideTransaction(entityManager -> entityManager.persist(item));
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteItem(int id) {
        try{
            CommentaryUser item = entityManager.find(CommentaryUser.class, id);
            entityManager.getTransaction().begin();
            entityManager.remove(item);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean updateItem(CommentaryUser item) {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(item);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public List<CommentaryUser> getAllItems() {
        List<CommentaryUser> items = new ArrayList<>();
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<CommentaryUser> query = entityManager.createQuery("SELECT a FROM  CommentaryUser a",CommentaryUser.class);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

    @Override
    public CommentaryUser getItemById(int id) {
        try{
            return entityManager.find(CommentaryUser.class, id);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CommentaryUser> getWhereItems(int max, String where, String order) {
        List<CommentaryUser> items = new ArrayList<>();
        String w;
        if(order == null){
            w = String.format("SELECT a FROM  CommentaryUser a WHERE %s",where);
        }else{
            w = String.format("SELECT a FROM  CommentaryUser a WHERE %s ORDER BY %s",where,order);
        }
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<CommentaryUser> query= entityManager.createQuery(w,CommentaryUser.class).setMaxResults(max);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }
}
