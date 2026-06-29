package com.openapifuse.generated.jsonplaceholder.jsonplaceholder.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration("jsonplaceholderTargetApiClientConfig")
public class TargetApiClientConfig {

    @Bean("jsonplaceholderTargetApiClient")
    public TargetApiClient jsonplaceholderTargetApiClient(
            RestClient.Builder builder,
            @Value("${target.api.jsonplaceholder.base-url}") String baseUrl,
            @Value("${target.api.jsonplaceholder.connect-timeout-ms:3000}") int connectTimeoutMs,
            @Value("${target.api.jsonplaceholder.read-timeout-ms:5000}") int readTimeoutMs,
            @Value("${target.api.jsonplaceholder.auth.type:NONE}") String authType,
            @Value("${target.api.jsonplaceholder.auth.header-name:X-API-Key}") String authHeaderName,
            @Value("${target.api.jsonplaceholder.auth.api-key:}") String authApiKey,
            @Value("${target.api.jsonplaceholder.auth.token:}") String authToken,
            @Value("${target.api.jsonplaceholder.auth.username:}") String authUsername,
            @Value("${target.api.jsonplaceholder.auth.password:}") String authPassword) {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeoutMs);
        factory.setReadTimeout(readTimeoutMs);

        RestClient restClient = builder
            .requestFactory(factory)
            .requestInterceptor(new TargetApiAuthInterceptor(
                authType, authHeaderName, authApiKey, authToken, authUsername, authPassword))
            .build();
        return new TargetApiClient(restClient, baseUrl);
    }
}
