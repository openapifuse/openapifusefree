package com.openapifuse.generated.jsonplaceholder_3.jsonplaceholder_3.controller;

import com.openapifuse.generated.jsonplaceholder_3.jsonplaceholder_3.client.TargetApiClient;
import com.openapifuse.generated.jsonplaceholder_3.client.TargetApiResponse;
import com.openapifuse.generated.jsonplaceholder_3.jsonplaceholder_3.mapper.Jsonplaceholder3Mapper;
import com.openapifuse.generated.jsonplaceholder_3.jsonplaceholder_3.mapper.Targetoperationjsonplaceholder;
import com.openapifuse.generated.jsonplaceholder_3.jsonplaceholder_3.mapper.Targetoperationjsonplaceholder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Jsonplaceholder3Controller {

    private final Jsonplaceholder3Mapper mapper = new Jsonplaceholder3Mapper();
    private final TargetApiClient targetApiClient;
    private final ObjectMapper objectMapper;

    public Jsonplaceholder3Controller(@Qualifier("jsonplaceholder3TargetApiClient") TargetApiClient targetApiClient, ObjectMapper objectMapper) {
        this.targetApiClient = targetApiClient;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/jsonplaceholder-3/transform")
    public ResponseEntity<byte[]> transform(@RequestBody Targetoperationjsonplaceholder body) {
        Targetoperationjsonplaceholder mapped = mapper.map(body);
        try {
            String json = objectMapper.writeValueAsString(mapped);
            TargetApiResponse response = targetApiClient.send(json);
            return relay(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize mapped response", e);
        }
    }

    private static final java.util.Set<String> HOP_BY_HOP = java.util.Set.of(
        "content-length", "transfer-encoding", "connection", "keep-alive");

    private static ResponseEntity<byte[]> relay(TargetApiResponse response) {
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(response.status());
        if (response.headers() != null) {
            response.headers().forEach((name, values) -> {
                if (name != null && values != null && !HOP_BY_HOP.contains(name.toLowerCase())) {
                    builder.header(name, values.toArray(new String[0]));
                }
            });
        }
        return builder.body(response.body());
    }
}
