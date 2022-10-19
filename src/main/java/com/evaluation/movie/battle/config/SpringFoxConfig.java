package com.evaluation.movie.battle.config;


import com.evaluation.movie.battle.config.external.properties.swagger.SwaggerConfigurations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@EnableSwagger2
@Configuration
public class SpringFoxConfig {

    private final SwaggerConfigurations configurations;

    public SpringFoxConfig(SwaggerConfigurations configurations){
        this.configurations = configurations;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.securityContexts(Arrays.asList(securityContext())) //TODO Remover quando ativar auth
                //.securitySchemes(Arrays.asList(apiKey())) //TODO Remover quando ativar auth
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.evaluation.movie.battle"))
                .build();
    }

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider) {
        return () -> {
            SwaggerResource wsResource = new SwaggerResource();
            wsResource.setName("movie-game-application");
            wsResource.setSwaggerVersion("2.0");
            wsResource.setUrl("/swagger.json");

            List<SwaggerResource> resources = new ArrayList<>(defaultResourcesProvider.get());
            // resources.clear(); //Remover o resource default da aplicação. Teplate padrao Swagger.
            resources.add(wsResource);
            return resources;
        };
    }

    /** //TODO Remover quando ativar auth
    private ApiKey apiKey() {
        return new ApiKey(configurations.getSwaggerAuth().getApiKey().getName(),
                          configurations.getSwaggerAuth().getApiKey().getKeyName(),
                          configurations.getSwaggerAuth().getApiKey().getPass());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                configurations.getSwaggerAuth().getDefaultAuth().getAuthorizationScope(),
                configurations.getSwaggerAuth().getDefaultAuth().getAuthorizationDescription());

        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference(
                configurations.getSwaggerAuth().getDefaultAuth().getSecurityReference(), authorizationScopes));
    } */

}