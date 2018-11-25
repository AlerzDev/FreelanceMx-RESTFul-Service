package repositories;

import bases.RepositoryBase;
import entities.Commentary;
import interfaces.RepositoryApi;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CommentaryRepository extends RepositoryBase implements RepositoryApi<Commentary> {


    @Override
    public boolean insertItem(Commentary item) {
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
            Commentary item = entityManager.find(Commentary.class, id);
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
    public boolean updateItem(Commentary item) {
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
    public List<Commentary> getAllItems() {
        List<Commentary> items = new ArrayList<>();
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<Commentary> query = entityManager.createQuery("SELECT a FROM  StatusProject a",Commentary.class);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

    @Override
    public Commentary getItemById(int id) {
        try{
            return entityManager.find(Commentary.class, id);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Commentary> getWhereItems(int max, String where, String order) {
        List<Commentary> items = new ArrayList<>();
        String w;
        if(order == null){
            w = String.format("SELECT a FROM  Commentary a WHERE %s",where);
        }else{
            w = String.format("SELECT a FROM  Commentary a WHERE %s ORDER BY %s",where,order);
        }
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<Commentary> query= entityManager.createQuery(w,Commentary.class).setMaxResults(max);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }
}
