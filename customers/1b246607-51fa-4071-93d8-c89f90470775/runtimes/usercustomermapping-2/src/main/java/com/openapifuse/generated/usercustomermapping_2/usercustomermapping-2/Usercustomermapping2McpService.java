package com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2;

import com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.client.TargetApiClient;
import com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.mapper.Usercustomermapping2Mapper;
import com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.mapper.UpdatesourceApi;
import com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.model.UpdatesourceApiInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Usercustomermapping2McpService {

    private final Usercustomermapping2Mapper mapper = new Usercustomermapping2Mapper();
    private final ObjectMapper objectMapper;
    private final Validator validator;
    private final TargetApiClient targetApiClient;

    public Usercustomermapping2McpService(ObjectMapper objectMapper, Validator validator,
            @Qualifier("usercustomermapping2TargetApiClient") TargetApiClient targetApiClient) {
        this.objectMapper = objectMapper;
        this.validator = validator;
        this.targetApiClient = targetApiClient;
    }

    @Tool(name = "user_customer_mapping", description = "Map user to customer object")
    public String transform(UpdatesourceApiInput input) {
        Set<ConstraintViolation<UpdatesourceApiInput>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining(", "));
            throw new IllegalArgumentException("Validation failed: " + errors);
        }
        try {
            UpdatesourceApi source = objectMapper.convertValue(input, UpdatesourceApi.class);
            String mappedJson = objectMapper.writeValueAsString(mapper.map(source));
            return targetApiClient.send(mappedJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Output serialization failed", e);
        }
    }
}
