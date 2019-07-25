package com.example.togepi.controller;

import com.example.togepi.service.MainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    @Qualifier("defaultMainService")
    private MainService mainService;

    @Autowired
    @Qualifier("secondMainService")
    private MainService secondMainService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello() throws Exception {
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Test-Header", "TestHeader");

        try {
            final String responseBody = objectMapper.writeValueAsString(mainService.hello());
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping(value = "/hello2")
    public ResponseEntity<String> secondHello() {
        final String hello = secondMainService.hello();
        return ResponseEntity.ok(hello);
    }
}
