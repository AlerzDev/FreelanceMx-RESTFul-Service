package repositories;

import bases.RepositoryBase;
import entities.Project;
import interfaces.RepositoryApi;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository extends RepositoryBase implements RepositoryApi<Project> {

    @Override
    public boolean insertItem(Project item) {
        try{
            return executeInsideTransaction(entityManager -> entityManager.persist(item));
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public long insertItemGetId(Project item){
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
            Project item = entityManager.find(Project.class, id);
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
    public boolean updateItem(Project item) {
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
    public List<Project> getAllItems() {
        List<Project> items = new ArrayList<>();
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<Project> query = entityManager.createQuery("SELECT a FROM  Project a",Project.class);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

    @Override
    public Project getItemById(int id) {
        try{
            return entityManager.find(Project.class, id);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public Project getItemById(long id) {
        try{
            return entityManager.find(Project.class, id);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Project> getWhereItems(int max, String where, String order) {
        List<Project> items = new ArrayList<>();
        String w;
        if(order == null){
            w = String.format("SELECT a FROM  Project a WHERE %s",where);
        }else{
            w = String.format("SELECT a FROM  Project a WHERE %s ORDER BY %s",where,order);
        }
        try{
            entityManager.clear();
            entityManager.getTransaction().begin();
            TypedQuery<Project> query= entityManager.createQuery(w,Project.class).setMaxResults(max);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  items;
    }

}
