package br.unitins.top1.resource;

import br.unitins.top1.dto.FeatureDTO;
import br.unitins.top1.dto.FeatureResponseDTO;
import br.unitins.top1.model.Feature;
import br.unitins.top1.service.FeatureService;
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

@Path("/features")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FeatureResource {

    @Inject
    FeatureService service;

    @GET
    public Response list() {
        return Response.ok(service.findAll().stream()
                .map(FeatureResponseDTO::fromEntity)
                .toList()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(FeatureResponseDTO.fromEntity(service.findById(id))).build();
    }

    @GET
    @Path("/search")
    public Response findByName(@QueryParam("name") String name) {
        return Response.ok(service.findByName(name).stream()
                .map(FeatureResponseDTO::fromEntity)
                .toList()).build();
    }

    @POST
    public Response create(@Valid FeatureDTO dto) {
        Feature feature = new Feature();
        feature.setName(dto.name());
        feature.setDescription(dto.description());
        return Response.status(Response.Status.CREATED)
                .entity(FeatureResponseDTO.fromEntity(service.create(feature)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid FeatureDTO dto) {
        Feature feature = new Feature();
        feature.setName(dto.name());
        feature.setDescription(dto.description());
        service.update(id, feature);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
