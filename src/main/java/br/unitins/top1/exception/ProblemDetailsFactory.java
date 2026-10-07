package br.unitins.top1.exception;

import java.util.List;

import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

public final class ProblemDetailsFactory {

    public static final String PROBLEM_JSON = "application/problem+json";

    private ProblemDetailsFactory() {
    }

    public static ProblemDetails create(String type, String title, Status status, String detail, UriInfo uriInfo) {
        return create(type, title, status, detail, uriInfo, List.of());
    }

    public static ProblemDetails create(
            String type,
            String title,
            Status status,
            String detail,
            UriInfo uriInfo,
            List<Violation> violations) {
        return new ProblemDetails(
                type,
                title,
                status.getStatusCode(),
                detail,
                uriInfo.getPath(),
                violations);
    }
}
