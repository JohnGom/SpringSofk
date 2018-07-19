package com.sofka.sofkianos.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ControllerRouter {

    @Bean
    public RouterFunction<ServerResponse> route(ControllerHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/create")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::savePerson)
                .andRoute(RequestPredicates.GET("/getMembers")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), handler::getMembers)
                .andRoute(RequestPredicates.DELETE("/deletePerson")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), handler::deletePerson)
                .andRoute(RequestPredicates.PUT("/updatePerson")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::updatePerson);
    }
}
