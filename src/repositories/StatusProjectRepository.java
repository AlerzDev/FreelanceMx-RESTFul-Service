package repositories;

import bases.RepositoryBase;
import entities.StatusProject;
import interfaces.RepositoryApi;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class StatusProjectRepository extends RepositoryBase implements RepositoryApi<StatusProject> {

    @Override
    public boolean insertItem(StatusProject item) {
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
            StatusProject item = entityManager.find(StatusProject.class, id);
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
    public boolean updateItem(StatusProject item) {
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
    public List<StatusProject> getAllItems() {
        List<StatusProject> items = new ArrayList<>();
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<StatusProject> query = entityManager.createQuery("SELECT a FROM  StatusProject a",StatusProject.class);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

    @Override
    public StatusProject getItemById(int id) {
        try{
            return entityManager.find(StatusProject.class, id);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StatusProject> getWhereItems(int max, String where, String order) {
        List<StatusProject> items = new ArrayList<>();
        String w;
        if(order == null){
            w = String.format("SELECT a FROM  StatusProject a WHERE %s",where);
        }else{
            w = String.format("SELECT a FROM  StatusProject a WHERE %s ORDER BY %s",where,order);
        }
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<StatusProject> query= entityManager.createQuery(w,StatusProject.class).setMaxResults(max);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

}
