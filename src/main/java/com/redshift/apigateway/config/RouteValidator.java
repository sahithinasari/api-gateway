package com.redshift.apigateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteValidator {

    private static final List<String> openEndpoints = List.of(
            "/auth/login",
            "/auth/register"
    );

    public boolean isSecured(ServerHttpRequest request) {
        return openEndpoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
    }
}
