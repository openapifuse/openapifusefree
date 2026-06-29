package com.openapifuse.generated.jsonplaceholder_3.jsonplaceholder_3.client;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Applies the configured outbound credential to every call this mapping's TargetApiClient makes
 * (CAP-ADR-1.16). NONE = no auth header. No retry / on-status / refresh — pure header injection.
 */
public class TargetApiAuthInterceptor implements ClientHttpRequestInterceptor {

    private final String authType;
    private final String headerName;
    private final String apiKey;
    private final String token;
    private final String username;
    private final String password;

    public TargetApiAuthInterceptor(String authType, String headerName, String apiKey,
            String token, String username, String password) {
        this.authType = authType == null ? "NONE" : authType.trim().toUpperCase();
        this.headerName = (headerName == null || headerName.isBlank()) ? "X-API-Key" : headerName;
        this.apiKey = apiKey;
        this.token = token;
        this.username = username;
        this.password = password;
    }

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        switch (authType) {
            case "API_KEY" -> {
                if (apiKey != null && !apiKey.isBlank()) {
                    request.getHeaders().set(headerName, apiKey);
                }
            }
            case "BEARER", "OAUTH2_STATIC" -> {
                if (token != null && !token.isBlank()) {
                    // Tolerate a token that already carries a "Bearer " prefix (common misconfiguration)
                    // instead of emitting "Bearer Bearer {token}".
                    String bare = token.trim();
                    if (bare.regionMatches(true, 0, "Bearer ", 0, 7)) {
                        bare = bare.substring(7).trim();
                    }
                    request.getHeaders().set("Authorization", "Bearer " + bare);
                }
            }
            case "BASIC" -> {
                if (username != null && !username.isBlank()) {
                    String pass = password == null ? "" : password;
                    String credentials = Base64.getEncoder()
                        .encodeToString((username + ":" + pass).getBytes(StandardCharsets.UTF_8));
                    request.getHeaders().set("Authorization", "Basic " + credentials);
                }
            }
            default -> {
                // NONE (or unknown) — no auth header.
            }
        }
        return execution.execute(request, body);
    }
}
