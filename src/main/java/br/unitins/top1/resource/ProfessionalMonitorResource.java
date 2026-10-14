package br.unitins.top1.resource;

import br.unitins.top1.dto.ProfessionalMonitorDTO;
import br.unitins.top1.dto.ProfessionalMonitorResponseDTO;
import br.unitins.top1.model.HardwareSpec;
import br.unitins.top1.model.PanelType;
import br.unitins.top1.model.ProfessionalMonitor;
import br.unitins.top1.service.AccessoryService;
import br.unitins.top1.service.BrandService;
import br.unitins.top1.service.FeatureService;
import br.unitins.top1.service.ProfessionalMonitorService;
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

@Path("/professional-monitors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfessionalMonitorResource {

    @Inject
    ProfessionalMonitorService service;

    @Inject
    BrandService brandService;

    @Inject
    FeatureService featureService;

    @Inject
    AccessoryService accessoryService;

    @GET
    public Response list() {
        return Response.ok(service.findAll().stream()
                .map(ProfessionalMonitorResponseDTO::fromEntity)
                .toList()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(ProfessionalMonitorResponseDTO.fromEntity(service.findById(id))).build();
    }

    @GET
    @Path("/search")
    public Response findByName(@QueryParam("name") String name) {
        return Response.ok(service.findByName(name).stream()
                .map(ProfessionalMonitorResponseDTO::fromEntity)
                .toList()).build();
    }

    @POST
    public Response create(@Valid ProfessionalMonitorDTO dto) {
        ProfessionalMonitor monitor = toEntity(dto);
        return Response.status(Response.Status.CREATED)
                .entity(ProfessionalMonitorResponseDTO.fromEntity(service.create(monitor)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid ProfessionalMonitorDTO dto) {
        ProfessionalMonitor monitor = toEntity(dto);
        service.update(id, monitor);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    private ProfessionalMonitor toEntity(ProfessionalMonitorDTO dto) {
        ProfessionalMonitor monitor = new ProfessionalMonitor();
        monitor.setName(dto.name());
        monitor.setPrice(dto.price());
        monitor.setScreenSize(dto.screenSize());
        monitor.setPanelType(PanelType.fromId(dto.idPanelType()));
        monitor.setBrand(brandService.findById(dto.idBrand()));
        monitor.setColorAccuracy(dto.colorAccuracy());
        monitor.setHeightAdjustment(dto.heightAdjustment());

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
