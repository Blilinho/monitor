package br.unitins.top1.exception;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(NotFoundException exception) {
        ProblemDetails problem = ProblemDetailsFactory.create(
                "urn:problem-type:not-found",
                "Resource not found",
                Status.NOT_FOUND,
                detail(exception, "The requested resource was not found."),
                uriInfo);

        return Response.status(Status.NOT_FOUND)
                .type(ProblemDetailsFactory.PROBLEM_JSON)
                .entity(problem)
                .build();
    }

    private String detail(NotFoundException exception, String fallback) {
        return exception.getMessage() == null || exception.getMessage().isBlank()
                ? fallback
                : exception.getMessage();
    }
}
