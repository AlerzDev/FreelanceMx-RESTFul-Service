package repositories;


import bases.RepositoryBase;
import entities.OffersProject;
import interfaces.RepositoryApi;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class OffersProjectRepository extends RepositoryBase implements RepositoryApi<OffersProject> {

    @Override
    public boolean insertItem(OffersProject item) {
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
            OffersProject item = entityManager.find(OffersProject.class, id);
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
    public boolean updateItem(OffersProject item) {
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
    public List<OffersProject> getAllItems() {
        List<OffersProject> items = new ArrayList<>();
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<OffersProject> query = entityManager.createQuery("SELECT a FROM  OffersProject a",OffersProject.class);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

    @Override
    public OffersProject getItemById(int id) {
        try{
            return entityManager.find(OffersProject.class, id);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OffersProject> getWhereItems(int max, String where, String order) {
        List<OffersProject> items = new ArrayList<>();
        String w;
        if(order == null){
            w = String.format("SELECT a FROM  OffersProject a WHERE %s",where);
        }else{
            w = String.format("SELECT a FROM  OffersProject a WHERE %s ORDER BY %s",where,order);
        }
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<OffersProject> query= entityManager.createQuery(w,OffersProject.class).setMaxResults(max);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

}
