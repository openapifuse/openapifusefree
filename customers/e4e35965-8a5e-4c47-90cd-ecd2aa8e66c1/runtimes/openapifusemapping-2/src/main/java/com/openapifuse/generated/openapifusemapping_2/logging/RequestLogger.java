package com.openapifuse.generated.openapifusemapping_2.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@Component
public class RequestLogger implements ClientHttpRequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestLogger.class);

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        String correlationId = MDC.get("correlationId");
        boolean createdCorrelationId = false;
        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
            MDC.put("correlationId", correlationId);
            createdCorrelationId = true;
        }

        String method = request.getMethod().name();
        String path = extractPath(request.getURI());
        long start = System.currentTimeMillis();

        try {
            ClientHttpResponse response = execution.execute(request, body);

            long durationMs = System.currentTimeMillis() - start;
            int statusCode = response.getStatusCode().value();

            log.info("method={} path={} status={} durationMs={} correlationId={}",
                    method, path, statusCode, durationMs, correlationId);

            return response;
        } catch (IOException ex) {
            long durationMs = System.currentTimeMillis() - start;
            log.warn("method={} path={} status={} durationMs={} correlationId={}",
                    method, path, "IO_ERROR", durationMs, correlationId);
            throw ex;
        } finally {
            if (createdCorrelationId) {
                MDC.remove("correlationId");
            }
        }
    }

    private static String extractPath(URI uri) {
        String path = uri.getPath();
        return (path == null || path.isBlank()) ? "/" : path;
    }
}
