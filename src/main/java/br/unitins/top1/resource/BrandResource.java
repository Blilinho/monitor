package br.unitins.top1.resource;

import br.unitins.top1.dto.BrandDTO;
import br.unitins.top1.dto.BrandResponseDTO;
import br.unitins.top1.model.Brand;
import br.unitins.top1.service.BrandService;
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

@Path("/brands")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BrandResource {

    @Inject
    BrandService service;

    @GET
    public Response list() {
        return Response.ok(service.findAll().stream()
                .map(BrandResponseDTO::fromEntity)
                .toList()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(BrandResponseDTO.fromEntity(service.findById(id))).build();
    }

    @GET
    @Path("/search")
    public Response findByName(@QueryParam("name") String name) {
        return Response.ok(service.findByName(name).stream()
                .map(BrandResponseDTO::fromEntity)
                .toList()).build();
    }

    @POST
    public Response create(@Valid BrandDTO dto) {
        Brand brand = new Brand();
        brand.setName(dto.name());
        brand.setCountry(dto.country());
        brand.setWebsite(dto.website());
        return Response.status(Response.Status.CREATED)
                .entity(BrandResponseDTO.fromEntity(service.create(brand)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid BrandDTO dto) {
        Brand brand = new Brand();
        brand.setName(dto.name());
        brand.setCountry(dto.country());
        brand.setWebsite(dto.website());
        service.update(id, brand);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
