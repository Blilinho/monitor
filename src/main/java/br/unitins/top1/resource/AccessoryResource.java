package br.unitins.top1.resource;

import br.unitins.top1.dto.AccessoryDTO;
import br.unitins.top1.dto.AccessoryResponseDTO;
import br.unitins.top1.model.Accessory;
import br.unitins.top1.service.AccessoryService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/accessories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccessoryResource {

    @Inject
    AccessoryService service;

    @GET
    public Response list() {
        return Response.ok(service.findAll().stream()
                .map(AccessoryResponseDTO::fromEntity)
                .toList()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(AccessoryResponseDTO.fromEntity(service.findById(id))).build();
    }

    @GET
    @Path("/search")
    public Response findByName(@QueryParam("name") String name) {
        return Response.ok(service.findByName(name).stream()
                .map(AccessoryResponseDTO::fromEntity)
                .toList()).build();
    }

    @GET
    @Path("/search/category")
    public Response findByCategory(@QueryParam("category") String category) {
        return Response.ok(service.findByCategory(category).stream()
                .map(AccessoryResponseDTO::fromEntity)
                .toList()).build();
    }

    @POST
    public Response create(@Valid AccessoryDTO dto) {
        Accessory accessory = new Accessory();
        accessory.setName(dto.name());
        accessory.setCategory(dto.category());
        accessory.setPrice(dto.price());
        accessory.setDescription(dto.description());
        return Response.status(Response.Status.CREATED)
                .entity(AccessoryResponseDTO.fromEntity(service.create(accessory)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid AccessoryDTO dto) {
        Accessory accessory = new Accessory();
        accessory.setName(dto.name());
        accessory.setCategory(dto.category());
        accessory.setPrice(dto.price());
        accessory.setDescription(dto.description());
        service.update(id, accessory);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
