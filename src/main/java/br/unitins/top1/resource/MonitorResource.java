package br.unitins.top1.resource;

import java.util.List;

import br.unitins.top1.model.Monitor;
import br.unitins.top1.repository.MonitorRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
public class MonitorResource {
    
    @Inject 
    MonitorRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Monitor> list() {
        return repository.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Monitor findIdMonitor(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Monitor createMonitor(Monitor monitor) {
        repository.persist(monitor);
        return monitor;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void updateMonitor(@PathParam("id") Long id, Monitor monitor) {
        Monitor monitorexisting = repository.findById(id);
        if (monitorexisting == null) {
            throw new RuntimeException("Monitor not found");
        }
        monitorexisting.setName(monitor.getName());
        monitorexisting.setBrand(monitor.getBrand());
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteMonitor(@PathParam("id") Long id) {
        repository.deleteById(id);
    }
}

