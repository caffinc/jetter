package com.caffinc.jetter.helper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Sriram
 * @since 4/28/2016
 */
@Path("/dummy")
public class DummyResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(@QueryParam("msg") String message) {
        DummyResponse response = new DummyResponse();
        response.setMessage(message);
        return Response.ok(response).build();
    }
}
