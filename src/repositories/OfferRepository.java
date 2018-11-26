package repositories;


import bases.RepositoryBase;
import entities.Offer;
import interfaces.RepositoryApi;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class OfferRepository extends RepositoryBase implements RepositoryApi<Offer> {

    @Override
    public boolean insertItem(Offer item) {
        try{
            return executeInsideTransaction(entityManager -> entityManager.persist(item));
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public long insertItemGetId(Offer item){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(item);
            entityManager.getTransaction().commit();
            return item.getId();
        }catch (Exception ex){
            return  0;
        }
    }

    @Override
    public boolean deleteItem(int id) {
        try{
            Offer item = entityManager.find(Offer.class, id);
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
    public boolean updateItem(Offer item) {
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
    public List<Offer> getAllItems() {
        List<Offer> items = new ArrayList<>();
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<Offer> query = entityManager.createQuery("SELECT a FROM  Offer a",Offer.class);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

    @Override
    public Offer getItemById(int id) {
        try{
            return entityManager.find(Offer.class, id);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Offer> getWhereItems(int max, String where, String order) {
        List<Offer> items = new ArrayList<>();
        String w;
        if(order == null){
            w = String.format("SELECT a FROM  Offer a WHERE %s",where);
        }else{
            w = String.format("SELECT a FROM  Offer a WHERE %s ORDER BY %s",where,order);
        }
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<Offer> query= entityManager.createQuery(w,Offer.class).setMaxResults(max);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }
}
