package br.unitins.top1.exception;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(BadRequestException exception) {
        ProblemDetails problem = ProblemDetailsFactory.create(
                "urn:problem-type:bad-request",
                "Bad request",
                Status.BAD_REQUEST,
                detail(exception, "The request could not be processed."),
                uriInfo);

        return Response.status(Status.BAD_REQUEST)
                .type(ProblemDetailsFactory.PROBLEM_JSON)
                .entity(problem)
                .build();
    }

    private String detail(BadRequestException exception, String fallback) {
        return exception.getMessage() == null || exception.getMessage().isBlank()
                ? fallback
                : exception.getMessage();
    }
}
