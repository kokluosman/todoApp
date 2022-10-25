package com.springtodoapp.configuration;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfiguration {


    public static final String AUTHORIZATION_HEADER = "Authorization";


    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springtodoapp"))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Spring Boot Todo Rest API's",
                "Spring Boot TodoApp Rest API Documentation",
                "1",
                "Terms of Service",
                new Contact("Osman Köklü","http:github.com/kokluosman","kokluosman01@gmail.com"),
                        "Licence of API","API Licence Url", Collections.emptyList());
    }

    private ApiKey apiKey(){
        return new ApiKey("JWT","Authorization","header");
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }
    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope =
                new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes =
                new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT",authorizationScopes));
    }
}
