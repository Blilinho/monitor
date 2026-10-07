package br.unitins.top1.exception;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(IllegalArgumentException exception) {
        ProblemDetails problem = ProblemDetailsFactory.create(
                "urn:problem-type:bad-request",
                "Bad request",
                Status.BAD_REQUEST,
                detail(exception, "The request contains invalid values."),
                uriInfo);

        return Response.status(Status.BAD_REQUEST)
                .type(ProblemDetailsFactory.PROBLEM_JSON)
                .entity(problem)
                .build();
    }

    private String detail(IllegalArgumentException exception, String fallback) {
        return exception.getMessage() == null || exception.getMessage().isBlank()
                ? fallback
                : exception.getMessage();
    }
}
