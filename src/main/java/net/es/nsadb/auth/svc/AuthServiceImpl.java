package net.es.nsadb.auth.svc;

import net.es.nsadb.PersistenceHolder;
import net.es.nsadb.auth.beans.AuthMethod;
import net.es.nsadb.auth.beans.AuthRecord;
import net.es.nsadb.auth.beans.CredentialRecord;
import net.es.nsadb.auth.beans.CredentialType;
import net.es.nsadb.auth.svc.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;
import java.util.List;

public class AuthServiceImpl implements AuthProviderService {

    public Response update(AuthRecord incoming) {
        EntityManager em = PersistenceHolder.getInstance().getEntityManager();
        em.getTransaction().begin();
        if (incoming.getId() != null) {
            AuthRecord old = em.find(AuthRecord.class, incoming.getId());
            if (old != null) {
                old.setMethod(incoming.getMethod());
                old.setNetworkId(incoming.getNetworkId());
                old.setCredentialRecordSet(incoming.getCredentialRecordSet());

            } else {
                System.out.println("could not locate old record with id "+ incoming.getId());
                Response r = Response.serverError().build();
                return r;
            }
        } else {
            em.persist(incoming);
        }

        em.getTransaction().commit();

        System.out.println("----update");
        Response r;
        r = Response.ok().build();
        return r;
    }

    public Response delete(Long id) {
        EntityManager em = PersistenceHolder.getInstance().getEntityManager();
        em.getTransaction().begin();
        AuthRecord ao = em.find(AuthRecord.class, id);
        if (ao == null) {
            System.out.println("record not found for id "+id);
        } else {
            em.remove(ao);
        }
        em.getTransaction().commit();


        System.out.println("----delete");
        Response r;
        r = Response.ok().build();
        return r;
    }

    public ListResponse list() {
        ListResponse resp = new ListResponse();
        EntityManager em = PersistenceHolder.getInstance().getEntityManager();
        em.getTransaction().begin();
        List<AuthRecord> recordList = em.createQuery("select c from AuthRecord c", AuthRecord.class).getResultList();
        for (AuthRecord ar :recordList) {
            for (CredentialRecord cr : ar.getCredentialRecordSet()) {
                cr.getCredential();
            }
        }
        em.getTransaction().commit();
        resp.setAuthRecords(recordList);


        return resp;
    }

    public AuthRecord byId(Long id) {
        AuthRecord ar = new AuthRecord();
        ar.setId(1L);
        ar.setMethod(AuthMethod.BASIC);
        ar.setNetworkId("foo");
        CredentialRecord cred = new CredentialRecord();
        cred.setId(2L);
        cred.setType(CredentialType.TOKEN);
        cred.setCredential("1231231");
        ar.getCredentialRecordSet().add(cred);
        System.out.println("----byId "+id);
        return ar;
    }

    public AuthRecord byNetwork(String network) {
        System.out.println("----byNetwork "+network);
        return null;
    }
}
