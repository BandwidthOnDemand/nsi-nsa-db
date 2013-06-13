package net.es.nsadb.nsa;

import net.es.nsadb.PersistenceHolder;

import net.es.nsadb.nsa.beans.NsaRecord;
import net.es.nsadb.nsa.svc.api.NsaByFilterRequest;
import net.es.nsadb.nsa.svc.api.NsaListResponse;
import net.es.nsadb.nsa.svc.api.NsaProviderService;
import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class NsaServiceImpl implements NsaProviderService {

    public Response update(NsaRecord incoming) {
        EntityManager em = PersistenceHolder.getInstance().getEntityManager();
        em.getTransaction().begin();
        if (incoming.getId() != null) {
            NsaRecord old = em.find(NsaRecord.class, incoming.getId());
            if (old != null) {
                old.setNetworkId(incoming.getNetworkId());
                old.setNsaId(incoming.getNsaId());
                old.setProviderUrl(incoming.getProviderUrl());

            } else {
                System.out.println("could not locate old record with id "+ incoming.getId());
                em.getTransaction().commit();
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
        NsaRecord ao = em.find(NsaRecord.class, id);
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

    public NsaListResponse list() {
        NsaListResponse resp = new NsaListResponse();
        EntityManager em = PersistenceHolder.getInstance().getEntityManager();
        em.getTransaction().begin();
        List<NsaRecord> recordList = em.createQuery("select c from NsaRecord c", NsaRecord.class).getResultList();
        for (NsaRecord ar :recordList) {
        }
        em.getTransaction().commit();
        resp.setNsaRecords(recordList);


        return resp;
    }

    public NsaRecord byId(Long id) {
        System.out.println("----byId "+id);

        EntityManager em = PersistenceHolder.getInstance().getEntityManager();
        em.getTransaction().begin();
        NsaRecord ar = em.find(NsaRecord.class, id);
        em.getTransaction().commit();

        return ar;
    }

    public NsaListResponse byFilter(NsaByFilterRequest request) {
        String networkId = request.getNetworkId();
        String nsaId = request.getNsaId();
        System.out.println("----byFilter "+networkId);
        NsaListResponse resp = new NsaListResponse();

        EntityManager em = PersistenceHolder.getInstance().getEntityManager();

        String query = "SELECT c FROM NsaRecord c WHERE ";

        List<String> clauses = new ArrayList<String>();
        if (networkId != null) {
            String clause = "c.networkId = '"+networkId+"'";
            clauses.add(clause);
        }
        if (nsaId != null) {
            String clause = "c.nsaId = '"+nsaId+"'";
            clauses.add(clause);
        }

        List<NsaRecord> recordList = new ArrayList<NsaRecord>();
        if (!clauses.isEmpty()) {
            em.getTransaction().begin();
            String clause = StringUtils.join(clauses, " AND ");
            query += "("+clause+")";
            System.out.println(clause);
            recordList = em.createQuery(query, NsaRecord.class).getResultList();
            em.getTransaction().commit();
        }


        resp.setNsaRecords(recordList);

        return resp;

    }
}
