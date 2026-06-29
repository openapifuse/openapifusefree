package com.openapifuse.generated.jsonplaceholder.jsonplaceholder.client;

import com.openapifuse.generated.jsonplaceholder.client.TargetApiException;
import com.openapifuse.generated.jsonplaceholder.client.TargetApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TargetApiClient {

    private final RestClient restClient;
    private final String baseUrl;

    public TargetApiClient(RestClient restClient, String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    /**
     * POST the mapped JSON to the upstream target and capture the response VERBATIM. The upstream status
     * is never inspected here (no on-status branching — that is the controller's job): any HTTP response
     * (2xx/4xx/5xx) is returned as a {@link TargetApiResponse}. Only a transport failure (no HTTP response)
     * throws — distinguishing read/connect timeout from other transport errors.
     */
    public TargetApiResponse send(String jsonBody) {
        try {
            return restClient.post()
                .uri(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .exchange((request, response) -> {
                    int status = response.getStatusCode().value();
                    byte[] responseBody = response.getBody() != null
                        ? response.getBody().readAllBytes()
                        : new byte[0];
                    // Capture ALL upstream headers so the relay is faithful (Location, Content-Disposition,
                    // custom metadata, …) — not just Content-Type. Hop-by-hop headers are dropped on relay.
                    Map<String, List<String>> headers = new LinkedHashMap<>(response.getHeaders());
                    return new TargetApiResponse(status, responseBody, headers);
                });
        } catch (ResourceAccessException ex) {
            throw TargetApiException.transport(
                "Target API transport failure: " + ex.getMessage(), isTimeout(ex), ex);
        } catch (RestClientException ex) {
            throw TargetApiException.transport(
                "Target API call failed: " + ex.getMessage(), false, ex);
        }
    }

    private static boolean isTimeout(Throwable ex) {
        Throwable cause = ex;
        while (cause != null) {
            if (cause instanceof SocketTimeoutException) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
    }
}
