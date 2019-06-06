package com.example.togepi.controller;

import com.example.togepi.service.MainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    @Qualifier("defaultMainService")
    private MainService mainService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value = "/hello")
    public ResponseEntity<String> hello() {
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Test-Header", "TestHeader");

        String responseBody = "";
        try {
            responseBody = objectMapper.writeValueAsString(mainService.hello());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(responseBody);
    }
}
