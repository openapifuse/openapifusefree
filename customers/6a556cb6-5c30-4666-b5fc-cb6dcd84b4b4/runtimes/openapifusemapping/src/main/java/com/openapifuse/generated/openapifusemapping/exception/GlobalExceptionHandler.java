package com.openapifuse.generated.openapifusemapping.exception;

import com.openapifuse.generated.openapifusemapping.client.TargetApiException;
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
    public ResponseEntity<Map<String, Object>> handleTargetApiException(
            TargetApiException ex, HttpServletRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "UPSTREAM_CALL_FAILED");
        body.put("status", 502);
        body.put("timestamp", Instant.now().toString());
        body.put("path", request.getRequestURI());
        body.put("correlationId", MDC.get("correlationId"));
        body.put("upstreamStatus", ex.getUpstreamStatus());

        return ResponseEntity.status(502).body(body);
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
