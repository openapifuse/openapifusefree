package com.openapifuse.generated.usercustomermapping.usercustomermapping.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration("usercustomermappingTargetApiClientConfig")
public class TargetApiClientConfig {

    @Bean("usercustomermappingTargetApiClient")
    public TargetApiClient usercustomermappingTargetApiClient(
            RestClient.Builder builder,
            @Value("${target.api.usercustomermapping.base-url}") String baseUrl,
            @Value("${target.api.usercustomermapping.connect-timeout-ms:3000}") int connectTimeoutMs,
            @Value("${target.api.usercustomermapping.read-timeout-ms:5000}") int readTimeoutMs) {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeoutMs);
        factory.setReadTimeout(readTimeoutMs);

        RestClient restClient = builder.requestFactory(factory).build();
        return new TargetApiClient(restClient, baseUrl);
    }
}
