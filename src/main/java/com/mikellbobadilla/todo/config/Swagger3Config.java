package com.mikellbobadilla.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class Swagger3Config {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Todo-API",
                "Application what allows to create tasks to do",
                "1.0.0",
                "",
                contact(),
                "MIT",
                "",
                new ArrayList<>()
        );
    }

    private Contact contact(){
        return new Contact(
            "Mikell",
            "https://github.com/mikellbobadilla",
            "mikellbobadilla@gmail.com"
        );
    }
}
