package com.openapifuse.generated.testingcap18.testingcap18.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration("testingcap18TargetApiClientConfig")
public class TargetApiClientConfig {

    @Bean("testingcap18TargetApiClient")
    public TargetApiClient testingcap18TargetApiClient(
            RestClient.Builder builder,
            @Value("${target.api.testingcap18.base-url}") String baseUrl,
            @Value("${target.api.testingcap18.connect-timeout-ms:3000}") int connectTimeoutMs,
            @Value("${target.api.testingcap18.read-timeout-ms:5000}") int readTimeoutMs,
            @Value("${target.api.testingcap18.auth.type:NONE}") String authType,
            @Value("${target.api.testingcap18.auth.header-name:X-API-Key}") String authHeaderName,
            @Value("${target.api.testingcap18.auth.api-key:}") String authApiKey,
            @Value("${target.api.testingcap18.auth.token:}") String authToken,
            @Value("${target.api.testingcap18.auth.username:}") String authUsername,
            @Value("${target.api.testingcap18.auth.password:}") String authPassword) {

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
