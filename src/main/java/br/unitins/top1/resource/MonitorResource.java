package br.unitins.top1.resource;

import java.util.List;

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

@Path("/monitors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MonitorResource {

    @Inject
    MonitorService service;

    @GET
    public List<MonitorResponseDTO> list() {
        return service.listAll().stream()
                .map(MonitorResponseDTO::fromEntity)
                .toList();
    }

    @GET
    @Path("/{id}")
    public MonitorResponseDTO findIdMonitor(@PathParam("id") Long id) {
        return MonitorResponseDTO.fromEntity(service.findById(id));
    }

    @GET
    @Path("/search/{nome}")
    public List<MonitorResponseDTO> findByNome(@PathParam("nome") String nome) {
        return service.findByName(nome).stream().map(MonitorResponseDTO::fromEntity).toList();
    }

    @GET
    @Path("/search/brand/{marca}")
    public List<MonitorResponseDTO> findByBrand(@PathParam("marca") String marca) {
        return service.findByBrand(marca).stream().map(MonitorResponseDTO::fromEntity).toList();
    }

    @POST
    public MonitorResponseDTO create(MonitorDTO dto) {
    Monitor monitor = new Monitor();
    monitor.setName(dto.name());
    monitor.setBrand(dto.brand());
    monitor.setPanelType(TipoPainel.fromId(dto.idPanelType()));
    return MonitorResponseDTO.fromEntity(service.create(monitor));
}

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id, MonitorDTO dto) {
    Monitor monitor = new Monitor();
    monitor.setName(dto.name());
    monitor.setBrand(dto.brand());
    monitor.setPanelType(TipoPainel.fromId(dto.idPanelType()));
    service.update(id, monitor);
}

    @DELETE
    @Path("/{id}")
    public void deleteMonitor(@PathParam("id") Long id) {
        service.delete(id);
    }
}