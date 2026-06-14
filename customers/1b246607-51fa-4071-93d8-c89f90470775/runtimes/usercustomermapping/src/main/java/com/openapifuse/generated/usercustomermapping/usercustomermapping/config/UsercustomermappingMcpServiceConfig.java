package com.openapifuse.generated.usercustomermapping.usercustomermapping.config;

import com.openapifuse.generated.usercustomermapping.usercustomermapping.UsercustomermappingMcpService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsercustomermappingMcpServiceConfig {

    @Bean
    public ToolCallbackProvider user_customer_mappingToolCallbackProvider(UsercustomermappingMcpService mcpService) {
        return MethodToolCallbackProvider.builder().toolObjects(mcpService).build();
    }
}
