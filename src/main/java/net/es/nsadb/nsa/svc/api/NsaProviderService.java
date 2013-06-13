package net.es.nsadb.nsa.svc.api;

import net.es.nsadb.nsa.beans.NsaRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public interface NsaProviderService {
    @GET
    @Path("/list")
    @Produces( "application/json" )
    NsaListResponse list();

    @GET
    @Path("/id/{id : \\d+}")
    @Produces( "application/json" )
    NsaRecord byId(@PathParam("id") Long id);

    @GET
    @Path("/delete/{id : \\d+}")
    Response delete(@PathParam("id") Long id);

    @POST
    @Path("/update")
    @Produces( "application/json" )
    @Consumes( "application/json" )
    Response update(NsaRecord obj);

    @POST
    @Path("/byFilter")
    @Produces( "application/json" )
    @Consumes( "application/json" )
    NsaListResponse byFilter(NsaByFilterRequest request);

}
