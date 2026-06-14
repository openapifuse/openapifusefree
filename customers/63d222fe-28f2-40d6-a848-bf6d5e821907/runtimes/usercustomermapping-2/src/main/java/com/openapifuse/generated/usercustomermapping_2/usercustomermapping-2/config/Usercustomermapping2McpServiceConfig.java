package com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.config;

import com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.Usercustomermapping2McpService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Usercustomermapping2McpServiceConfig {

    @Bean
    public ToolCallbackProvider map_user_customerToolCallbackProvider(Usercustomermapping2McpService mcpService) {
        return MethodToolCallbackProvider.builder().toolObjects(mcpService).build();
    }
}
