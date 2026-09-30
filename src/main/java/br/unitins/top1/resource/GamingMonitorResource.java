package br.unitins.top1.resource;

import br.unitins.top1.dto.GamingMonitorDTO;
import br.unitins.top1.service.GamingMonitorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/gaming-monitors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GamingMonitorResource {

    @Inject
    GamingMonitorService service;

    @POST
    public Response insert(GamingMonitorDTO dto) {
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }
}