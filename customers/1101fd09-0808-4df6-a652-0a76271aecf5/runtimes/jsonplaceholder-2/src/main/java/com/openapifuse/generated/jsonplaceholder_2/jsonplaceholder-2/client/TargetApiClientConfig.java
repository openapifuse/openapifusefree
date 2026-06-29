package com.openapifuse.generated.jsonplaceholder_2.jsonplaceholder_2.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration("jsonplaceholder2TargetApiClientConfig")
public class TargetApiClientConfig {

    @Bean("jsonplaceholder2TargetApiClient")
    public TargetApiClient jsonplaceholder2TargetApiClient(
            RestClient.Builder builder,
            @Value("${target.api.jsonplaceholder-2.base-url}") String baseUrl,
            @Value("${target.api.jsonplaceholder-2.connect-timeout-ms:3000}") int connectTimeoutMs,
            @Value("${target.api.jsonplaceholder-2.read-timeout-ms:5000}") int readTimeoutMs,
            @Value("${target.api.jsonplaceholder-2.auth.type:NONE}") String authType,
            @Value("${target.api.jsonplaceholder-2.auth.header-name:X-API-Key}") String authHeaderName,
            @Value("${target.api.jsonplaceholder-2.auth.api-key:}") String authApiKey,
            @Value("${target.api.jsonplaceholder-2.auth.token:}") String authToken,
            @Value("${target.api.jsonplaceholder-2.auth.username:}") String authUsername,
            @Value("${target.api.jsonplaceholder-2.auth.password:}") String authPassword) {

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
