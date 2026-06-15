package com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration("usercustomermapping2TargetApiClientConfig")
public class TargetApiClientConfig {

    @Bean("usercustomermapping2TargetApiClient")
    public TargetApiClient usercustomermapping2TargetApiClient(
            RestClient.Builder builder,
            @Value("${target.api.usercustomermapping-2.base-url}") String baseUrl,
            @Value("${target.api.usercustomermapping-2.connect-timeout-ms:3000}") int connectTimeoutMs,
            @Value("${target.api.usercustomermapping-2.read-timeout-ms:5000}") int readTimeoutMs) {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeoutMs);
        factory.setReadTimeout(readTimeoutMs);

        RestClient restClient = builder.requestFactory(factory).build();
        return new TargetApiClient(restClient, baseUrl);
    }
}
