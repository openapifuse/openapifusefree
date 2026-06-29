package com.openapifuse.generated.jsonplaceholder_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBeanNameGenerator(new MappingAwareBeanNameGenerator());
        app.run(args);
    }
}
