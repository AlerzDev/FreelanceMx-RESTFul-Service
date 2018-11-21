package repositories;

import bases.RepositoryBase;
import entities.UserFreelance;
import interfaces.RepositoryApi;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class UserFreelanceRepository extends RepositoryBase implements RepositoryApi<UserFreelance> {

    @Override
    public boolean insertItem(UserFreelance item) {
        try{
            return executeInsideTransaction(entityManager -> entityManager.persist(item));
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteItem(int id)
    {
        try{
            UserFreelance item = entityManager.find(UserFreelance.class, id);
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
    public  boolean updateItem(UserFreelance item){
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
    public List<UserFreelance> getAllItems()
    {
        List<UserFreelance> items = new ArrayList<>();
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<UserFreelance> query = entityManager.createQuery("SELECT a FROM UserFreelance a",UserFreelance.class);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

    @Override
    public UserFreelance getItemById(int id){
        try{
            return entityManager.find(UserFreelance.class, id);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserFreelance> getWhereItems(int max, String where, String order) {
        List<UserFreelance> items = new ArrayList<>();
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            String w = String.format("SELECT a FROM  UserFreelance a WHERE %s ORDER BY %s",where,order);
            TypedQuery<UserFreelance> query= entityManager.createNamedQuery(w,UserFreelance.class).setMaxResults(max);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

}
