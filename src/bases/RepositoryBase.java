package bases;

import interfaces.RepositoryApi;
import utils.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public class RepositoryBase  {
    private static final EntityManagerFactory emFactoryObj;

    protected EntityManager entityManager;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
    }

    public RepositoryBase(){
        entityManager = emFactoryObj.createEntityManager();
    }

    protected boolean executeInsideTransaction(Consumer<EntityManager> action) {
        try {
            entityManager.getTransaction().begin();
            action.accept(entityManager);
            entityManager.getTransaction().commit();
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }

}
