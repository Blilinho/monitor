package br.unitins.top1.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public record ProblemDetails(
        String type,
        String title,
        int status,
        String detail,
        String instance,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<Violation> violations) {
}
