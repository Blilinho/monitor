package br.unitins.top1.resource;

import java.util.List;

import br.unitins.top1.model.Monitor;
import br.unitins.top1.service.MonitorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/monitors")
public class MonitorResource {

    @Inject
    MonitorService service;

    @GET
    public List<Monitor> list() {
        return service.listAll();
    }

    @GET
    @Path("{id}")
    public Monitor findIdMonitor(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    public Monitor createMonitor(Monitor monitor) {
        return service.create(monitor);
    }

    @PUT
    @Path("/{id}")
    public void updateMonitor(@PathParam("id") Long id, Monitor monitor) {
        Monitor monitorexisting = service.findById(id);
        if (monitorexisting == null) {
            throw new RuntimeException("Monitor not found");
        }
        monitorexisting.setName(monitor.getName());
        monitorexisting.setBrand(monitor.getBrand());
    }

    @DELETE
    @Path("/{id}")

    public void deleteMonitor(@PathParam("id") Long id) {
        service.delete(id);
    }
}

