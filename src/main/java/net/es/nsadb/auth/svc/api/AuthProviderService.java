package net.es.nsadb.auth.svc.api;



import net.es.nsadb.auth.beans.AuthRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/")

public interface AuthProviderService {
    @GET
    @Path("/list")
    @Produces( "application/json" )
    AuthListResponse list();


    @GET
    @Path("/id/{id : \\d+}")
    @Produces( "application/json" )
    AuthRecord byId(@PathParam("id") Long id);

    @GET
    @Path("/delete/{id : \\d+}")
    Response delete(@PathParam("id") Long id);

    @POST
    @Path("/update")
    @Produces( "application/json" )
    @Consumes( "application/json" )
    Response update(AuthRecord obj);

    @POST
    @Path("/byFilter")
    @Produces( "application/json" )
    @Consumes( "application/json" )
    AuthListResponse byFilter(AuthByFilterRequest request);


}
