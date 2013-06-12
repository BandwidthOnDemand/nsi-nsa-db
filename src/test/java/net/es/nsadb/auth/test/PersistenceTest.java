package net.es.nsadb.auth.test;

import net.es.nsadb.auth.beans.AuthRecord;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceTest {
    @Test(groups = "db")
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("net.es.nsadb.auth");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        AuthRecord ar = new AuthRecord();
        ar.setNetworkId("foo");
        em.persist(ar);
        em.getTransaction().commit();


        em.close();

    }}
