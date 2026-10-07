package br.unitins.top1.resource;

import java.util.List;
import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class RootResource {

    @GET
    public Map<String, Object> home() {
        return Map.of(
                "message", "Monitor API is running",
                "endpoints", List.of(
                        "/monitors",
                        "/gaming-monitors",
                        "/professional-monitors",
                        "/q/swagger-ui"));
    }
}
