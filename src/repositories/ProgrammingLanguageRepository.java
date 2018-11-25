package repositories;

import bases.RepositoryBase;
import entities.ProgrammingLanguage;
import interfaces.RepositoryApi;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProgrammingLanguageRepository extends RepositoryBase implements RepositoryApi<ProgrammingLanguage> {

    @Override
    public boolean insertItem(ProgrammingLanguage item) {
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
            ProgrammingLanguage item = entityManager.find(ProgrammingLanguage.class, id);
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
    public boolean updateItem(ProgrammingLanguage item) {
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
    public List<ProgrammingLanguage> getAllItems() {
        List<ProgrammingLanguage> items = new ArrayList<>();
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<ProgrammingLanguage> query = entityManager.createQuery("SELECT a FROM  ProgrammingLanguage a",ProgrammingLanguage.class);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

    @Override
    public ProgrammingLanguage getItemById(int id) {
        try{
            return entityManager.find(ProgrammingLanguage.class, id);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProgrammingLanguage> getWhereItems(int max, String where, String order) {
        List<ProgrammingLanguage> items = new ArrayList<>();
        String w;
        if(order == null){
            w = String.format("SELECT a FROM  ProgrammingLanguage a WHERE %s",where);
        }else{
            w = String.format("SELECT a FROM  ProgrammingLanguage a WHERE %s ORDER BY %s",where,order);
        }
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<ProgrammingLanguage> query= entityManager.createQuery(w,ProgrammingLanguage.class).setMaxResults(max);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

}
