package com.mikellbobadilla.todo.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;

@Configuration
public class Swagger3Config {

    private ApiInfo apiInfo(){
        return ApiInfo.DEFAULT;
    }
}
