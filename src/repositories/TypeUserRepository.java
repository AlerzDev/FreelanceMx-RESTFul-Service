package repositories;

import bases.RepositoryBase;
import entities.TypeUser;
import interfaces.RepositoryApi;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class TypeUserRepository extends RepositoryBase implements RepositoryApi<TypeUser> {

    @Override
    public boolean insertItem(TypeUser item) {
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
            TypeUser item = entityManager.find(TypeUser.class, id);
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
    public  boolean updateItem(TypeUser item){
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
    public List<TypeUser> getAllItems()
    {
        List<TypeUser> items = new ArrayList<>();
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<TypeUser> query = entityManager.createQuery("SELECT a FROM  TypeUser a",TypeUser.class);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

    @Override
    public TypeUser getItemById(int id){
        try{
            return entityManager.find(TypeUser.class, id);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TypeUser> getWhereItems(int max, String where, String order) {
        List<TypeUser> items = new ArrayList<>();
        String w;
        if(order == null){
            w = String.format("SELECT a FROM  UserFreelance a WHERE %s",where);
        }else{
            w = String.format("SELECT a FROM  UserFreelance a WHERE %s ORDER BY %s",where,order);
        }
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<TypeUser> query= entityManager.createQuery(w,TypeUser.class).setMaxResults(max);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

}
