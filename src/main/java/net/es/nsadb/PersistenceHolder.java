package net.es.nsadb;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceHolder {
    private EntityManager em;
    private PersistenceHolder() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("net.es.nsadb.auth");
        em = emf.createEntityManager();
    }
    private static PersistenceHolder instance;
    public static PersistenceHolder getInstance() {
        if (instance == null) {
            instance = new PersistenceHolder();

        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return em;
    }


}
