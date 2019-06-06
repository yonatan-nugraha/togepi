package com.example.togepi.controller;

import com.example.togepi.model.dto.ZomatoResponse;
import com.example.togepi.service.AsyncHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AsyncHttpController {

    @Autowired
    private AsyncHttpService asyncHttpService;

    @GetMapping(value = "/http")
    public ZomatoResponse getRestaurant() {
        return asyncHttpService.getRestaurant().join();
    }
}
