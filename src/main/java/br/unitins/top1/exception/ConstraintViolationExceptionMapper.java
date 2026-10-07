package br.unitins.top1.exception;

import java.util.Comparator;
import java.util.List;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<Violation> violations = exception.getConstraintViolations().stream()
                .map(this::toViolation)
                .sorted(Comparator.comparing(Violation::field))
                .toList();

        ProblemDetails problem = ProblemDetailsFactory.create(
                "urn:problem-type:validation-error",
                "Validation failed",
                Status.BAD_REQUEST,
                "One or more fields failed validation.",
                uriInfo,
                violations);

        return Response.status(Status.BAD_REQUEST)
                .type(ProblemDetailsFactory.PROBLEM_JSON)
                .entity(problem)
                .build();
    }

    private Violation toViolation(ConstraintViolation<?> violation) {
        return new Violation(fieldName(violation.getPropertyPath()), violation.getMessage());
    }

    private String fieldName(Path path) {
        String field = path.toString();
        for (Path.Node node : path) {
            if (node.getName() != null && !node.getName().isBlank()) {
                field = node.getName();
            }
        }
        return field;
    }
}
