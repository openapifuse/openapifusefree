package com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.client;

import com.openapifuse.generated.usercustomermapping_2.client.TargetApiException;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

public class TargetApiClient {

    private final RestClient restClient;
    private final String baseUrl;

    public TargetApiClient(RestClient restClient, String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    public String send(String jsonBody) {
        try {
            return restClient.post()
                .uri(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonBody)
                .retrieve()
                .body(String.class);
        } catch (RestClientResponseException ex) {
            throw new TargetApiException(
                "Target API returned HTTP " + ex.getStatusCode().value(),
                ex.getStatusCode().value());
        } catch (RestClientException ex) {
            throw new TargetApiException(
                "Target API call failed: " + ex.getMessage(),
                null, ex);
        }
    }
}
