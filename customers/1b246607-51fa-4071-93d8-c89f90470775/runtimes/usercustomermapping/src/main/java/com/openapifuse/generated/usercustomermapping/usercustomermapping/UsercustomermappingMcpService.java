package com.openapifuse.generated.usercustomermapping.usercustomermapping;

import com.openapifuse.generated.usercustomermapping.usercustomermapping.client.TargetApiClient;
import com.openapifuse.generated.usercustomermapping.usercustomermapping.mapper.UsercustomermappingMapper;
import com.openapifuse.generated.usercustomermapping.usercustomermapping.mapper.UpdatesourceApi;
import com.openapifuse.generated.usercustomermapping.usercustomermapping.model.UpdatesourceApiInput;
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
public class UsercustomermappingMcpService {

    private final UsercustomermappingMapper mapper = new UsercustomermappingMapper();
    private final ObjectMapper objectMapper;
    private final Validator validator;
    private final TargetApiClient targetApiClient;

    public UsercustomermappingMcpService(ObjectMapper objectMapper, Validator validator,
            @Qualifier("usercustomermappingTargetApiClient") TargetApiClient targetApiClient) {
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
