package br.unitins.top1.resource;

import java.util.List;

import br.unitins.top1.model.Monitor;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/monitors")
public class MonitorResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Monitor> getAll() {
        return Monitor.listAll();
    }
}
