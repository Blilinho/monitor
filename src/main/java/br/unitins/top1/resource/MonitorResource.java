package br.unitins.top1.resource;

import br.unitins.top1.dto.MonitorDTO;
import br.unitins.top1.dto.MonitorResponseDTO;
import br.unitins.top1.model.HardwareSpec;
import br.unitins.top1.model.Monitor;
import br.unitins.top1.model.PanelType;
import br.unitins.top1.service.AccessoryService;
import br.unitins.top1.service.BrandService;
import br.unitins.top1.service.FeatureService;
import br.unitins.top1.service.MonitorService;
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

@Path("/monitors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MonitorResource {

    @Inject
    MonitorService service;

    @Inject
    BrandService brandService;

    @Inject
    FeatureService featureService;

    @Inject
    AccessoryService accessoryService;

    @GET
    public Response list() {
        return Response.ok(service.findAll().stream()
                .map(MonitorResponseDTO::fromEntity)
                .toList()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(MonitorResponseDTO.fromEntity(service.findById(id))).build();
    }

    @GET
    @Path("/search")
    public Response findByName(@QueryParam("name") String name) {
        return Response.ok(service.findByName(name).stream()
                .map(MonitorResponseDTO::fromEntity)
                .toList()).build();
    }

    @GET
    @Path("/search/brand")
    public Response findByBrandId(@QueryParam("brandId") Long brandId) {
        return Response.ok(service.findByBrandId(brandId).stream()
                .map(MonitorResponseDTO::fromEntity)
                .toList()).build();
    }

    @POST
    public Response create(@Valid MonitorDTO dto) {
        Monitor monitor = toEntity(dto);
        return Response.status(Response.Status.CREATED)
                .entity(MonitorResponseDTO.fromEntity(service.create(monitor)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid MonitorDTO dto) {
        Monitor monitor = toEntity(dto);
        service.update(id, monitor);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    private Monitor toEntity(MonitorDTO dto) {
        Monitor monitor = new Monitor();
        monitor.setName(dto.name());
        monitor.setPrice(dto.price());
        monitor.setScreenSize(dto.screenSize());
        monitor.setPanelType(PanelType.fromId(dto.idPanelType()));
        monitor.setBrand(brandService.findById(dto.idBrand()));

        if (dto.hardwareSpec() != null) {
            HardwareSpec spec = new HardwareSpec();
            spec.setWeight(dto.hardwareSpec().weight());
            spec.setDimensions(dto.hardwareSpec().dimensions());
            spec.setPowerConsumption(dto.hardwareSpec().powerConsumption());
            monitor.setHardwareSpec(spec);
        }

        if (dto.featureIds() != null && !dto.featureIds().isEmpty()) {
            monitor.setFeatures(dto.featureIds().stream()
                    .map(featureService::findById)
                    .toList());
        }

        if (dto.accessoryIds() != null && !dto.accessoryIds().isEmpty()) {
            monitor.setCompatibleAccessories(dto.accessoryIds().stream()
                    .map(accessoryService::findById)
                    .toList());
        }

        return monitor;
    }
}
