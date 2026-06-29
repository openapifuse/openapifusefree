package com.openapifuse.generated.jsonplaceholder_2.exception;

import com.openapifuse.generated.jsonplaceholder_2.client.TargetApiException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public final class GlobalExceptionHandler {

    @ExceptionHandler(TargetApiException.class)
    public ResponseEntity<?> handleTargetApiException(
            TargetApiException ex, HttpServletRequest request) {

        // Pass-through (defensive — REST controllers normally relay inline): relay the upstream response
        // verbatim — real status + raw body bytes + original Content-Type. Byte invariant — no envelope.
        if (!ex.isTransportFailure() && ex.getUpstreamStatus() != null) {
            ResponseEntity.BodyBuilder builder = ResponseEntity.status(ex.getUpstreamStatus());
            if (ex.getContentType() != null && !ex.getContentType().isBlank()) {
                builder.header("Content-Type", ex.getContentType());
            }
            return builder.body(ex.bodyBytes() != null ? ex.bodyBytes() : new byte[0]);
        }

        // Transport failure is the ONLY synthesized case (CAP-ADR-1.17): timeout → 504, other → 502.
        int status = ex.isTimeout() ? 504 : 502;
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", ex.isTimeout() ? "UPSTREAM_TIMEOUT" : "UPSTREAM_UNAVAILABLE");
        body.put("status", status);
        body.put("timestamp", Instant.now().toString());
        body.put("path", request.getRequestURI());
        body.put("correlationId", MDC.get("correlationId"));

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(
            ConstraintViolationException ex, HttpServletRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "VALIDATION_FAILED");
        body.put("status", 400);
        body.put("timestamp", Instant.now().toString());
        body.put("path", request.getRequestURI());
        body.put("correlationId", Objects.requireNonNullElse(MDC.get("correlationId"), "none"));

        List<Map<String, String>> violations = new ArrayList<>();
        ex.getConstraintViolations().forEach(violation -> {
            String rawPath = violation.getPropertyPath().toString();
            // Strip the method name prefix (e.g. "execute.arg0." or "execute.payload.") so
            // consumers receive just the field name, not Spring's internal proxy path.
            String field = rawPath.contains(".") ? rawPath.substring(rawPath.lastIndexOf('.') + 1) : rawPath;
            Map<String, String> v = new LinkedHashMap<>();
            v.put("field", field);
            v.put("message", violation.getMessage());
            violations.add(v);
        });
        body.put("violations", violations);

        return ResponseEntity.status(400).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "VALIDATION_FAILED");
        body.put("status", 400);
        body.put("timestamp", Instant.now().toString());
        body.put("path", request.getRequestURI());
        body.put("correlationId", Objects.requireNonNullElse(MDC.get("correlationId"), "none"));

        List<Map<String, String>> violations = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            Map<String, String> v = new LinkedHashMap<>();
            v.put("field", error.getField());
            v.put("message", error.getDefaultMessage());
            violations.add(v);
        });
        body.put("violations", violations);

        return ResponseEntity.status(400).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(
            Exception ex, HttpServletRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "INTERNAL_ERROR");
        body.put("status", 500);
        body.put("timestamp", Instant.now().toString());
        body.put("path", request.getRequestURI());
        body.put("correlationId", Objects.requireNonNullElse(MDC.get("correlationId"), "none"));

        return ResponseEntity.status(500).body(body);
    }
}
