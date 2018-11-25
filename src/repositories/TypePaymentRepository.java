package repositories;

import bases.RepositoryBase;
import entities.TypePayment;
import interfaces.RepositoryApi;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class TypePaymentRepository extends RepositoryBase implements RepositoryApi<TypePayment> {

    @Override
    public boolean insertItem(TypePayment item) {
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
            TypePayment item = entityManager.find(TypePayment.class, id);
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
    public boolean updateItem(TypePayment item) {
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
    public List<TypePayment> getAllItems() {
        List<TypePayment> items = new ArrayList<>();
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<TypePayment> query = entityManager.createQuery("SELECT a FROM  TypePayment a",TypePayment.class);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

    @Override
    public TypePayment getItemById(int id) {
        try{
            return entityManager.find(TypePayment.class, id);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TypePayment> getWhereItems(int max, String where, String order) {
        List<TypePayment> items = new ArrayList<>();
        String w;
        if(order == null){
            w = String.format("SELECT a FROM  TypePayment a WHERE %s",where);
        }else{
            w = String.format("SELECT a FROM  TypePayment a WHERE %s ORDER BY %s",where,order);
        }
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<TypePayment> query= entityManager.createQuery(w,TypePayment.class).setMaxResults(max);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

}
