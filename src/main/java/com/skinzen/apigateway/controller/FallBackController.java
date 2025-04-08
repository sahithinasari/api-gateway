package com.skinzen.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FallBackController {

    @RequestMapping(value = "/userServiceFallback", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> userServiceFallback() {
        return ResponseEntity.status(503).body("User Service is currently unavailable. Please try again later.");
    }

    @RequestMapping(value = "/fallback/orders", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> fallbackAllMethods() {
        return ResponseEntity.status(503).body("Order Service is currently unavailable. Please try again later.");
    }

    @RequestMapping(value = "/notificationServiceFallback", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> notificationServiceFallback() {
        return ResponseEntity.status(503).body("Notification Service is currently unavailable. Please try again later.");
    }

    @RequestMapping(value = "/authServiceFallback", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> authServiceFallback() {
        return ResponseEntity.status(503).body("Authentication Service is currently unavailable. Please try again later.");
    }

    @RequestMapping(value = "/defaultFallback", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> defaultFallback() {
        return ResponseEntity.status(503).body("Service is currently unavailable. Please try again later.");
    }
}
