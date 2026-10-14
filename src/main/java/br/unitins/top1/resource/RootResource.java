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
                "message", "Monitor E-Commerce Admin API is running",
                "endpoints", List.of(
                        "/brands",
                        "/features",
                        "/accessories",
                        "/monitors",
                        "/gaming-monitors",
                        "/professional-monitors",
                        "/q/swagger-ui"));
    }
}
