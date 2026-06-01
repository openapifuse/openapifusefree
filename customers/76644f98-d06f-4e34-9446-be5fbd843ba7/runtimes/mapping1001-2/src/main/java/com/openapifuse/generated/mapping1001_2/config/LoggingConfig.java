package com.openapifuse.generated.mapping1001_2.config;

import com.openapifuse.generated.mapping1001_2.logging.MdcTaskDecorator;
import com.openapifuse.generated.mapping1001_2.logging.RequestLogger;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class LoggingConfig {

    @Bean
    public RestClientCustomizer requestLoggerCustomizer(RequestLogger requestLogger) {
        return builder -> builder.requestInterceptor(requestLogger);
    }

    @Bean
    public ThreadPoolTaskExecutor applicationTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setTaskDecorator(new MdcTaskDecorator());
        executor.initialize();
        return executor;
    }
}
