package repositories;

import bases.RepositoryBase;
import entities.UserProject;
import interfaces.RepositoryApi;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class UserProjectRepository extends RepositoryBase implements RepositoryApi<UserProject> {

    @Override
    public boolean insertItem(UserProject item) {
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
            UserProject item = entityManager.find(UserProject.class, id);
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
    public boolean updateItem(UserProject item) {
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
    public List<UserProject> getAllItems() {
        List<UserProject> items = new ArrayList<>();
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<UserProject> query = entityManager.createQuery("SELECT a FROM  UserProject a",UserProject.class);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

    @Override
    public UserProject getItemById(int id) {
        try{
            return entityManager.find(UserProject.class, id);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserProject> getWhereItems(int max, String where, String order) {
        List<UserProject> items = new ArrayList<>();
        String w;
        if(order == null){
            w = String.format("SELECT a FROM  UserProject a WHERE %s",where);
        }else{
            w = String.format("SELECT a FROM  UserProject a WHERE %s ORDER BY %s",where,order);
        }
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<UserProject> query= entityManager.createQuery(w,UserProject.class).setMaxResults(max);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

}
