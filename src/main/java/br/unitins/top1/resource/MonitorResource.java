package br.unitins.top1.resource;

import br.unitins.top1.dto.MonitorDTO;
import br.unitins.top1.dto.MonitorResponseDTO;
import br.unitins.top1.model.Monitor;
import br.unitins.top1.model.TipoPainel;
import br.unitins.top1.service.MonitorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/monitors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MonitorResource {

    @Inject
    MonitorService service;

    @GET
    public Response list() {
        return Response.ok(service.listAll().stream()
                .map(MonitorResponseDTO::fromEntity)
                .toList()).build();
    }

    @GET
    @Path("/{id}")
    public Response findId(@PathParam("id") Long id) {
        return Response.ok(MonitorResponseDTO.fromEntity(service.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByName(nome).stream().map(MonitorResponseDTO::fromEntity).toList()).build();
    }

    @GET
    @Path("/search/brand/{marca}")
    public Response findByBrand(@PathParam("marca") String marca) {
        return Response.ok(service.findByBrand(marca).stream().map(MonitorResponseDTO::fromEntity).toList()).build();
    }

    @POST
    public Response create(MonitorDTO dto) {
    Monitor monitor = new Monitor();
    monitor.setName(dto.name());
    monitor.setBrand(dto.brand());
    monitor.setPanelType(TipoPainel.fromId(dto.idPanelType()));
    return Response.status(Response.Status.CREATED).entity(MonitorResponseDTO.fromEntity(service.create(monitor))).build();
}

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, MonitorDTO dto) {
    Monitor monitor = new Monitor();
    monitor.setName(dto.name());
    monitor.setBrand(dto.brand());
    monitor.setPanelType(TipoPainel.fromId(dto.idPanelType()));

    service.update(id, monitor);
    return Response.status(Response.Status.OK).entity(MonitorResponseDTO.fromEntity(service.update(id, monitor))).build();
}

    @DELETE
    @Path("/{id}")
    public Response deleteMonitor(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}