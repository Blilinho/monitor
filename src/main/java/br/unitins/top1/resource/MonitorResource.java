package br.unitins.top1.resource;

import java.util.List;

import br.unitins.top1.model.Monitor;
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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Monitor> list() {
        return Monitor.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Monitor findIdMonitor(@PathParam("id") Long id) {
        return Monitor.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Monitor createMonitor(Monitor monitor) {
        monitor.persist();
        return monitor;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void updateMonitor(@PathParam("id") Long id, Monitor monitor) {
        Monitor monitorexisting = Monitor.findById(id);
        if (monitorexisting == null) {
            throw new RuntimeException("Monitor not found");
        }
        monitorexisting.name = monitor.name;
        monitorexisting.brand = monitor.brand;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteMonitor(@PathParam("id") Long id) {
        Monitor.deleteById(id);
    }
}

