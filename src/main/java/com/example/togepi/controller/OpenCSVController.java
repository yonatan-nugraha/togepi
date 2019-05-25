package com.example.togepi.controller;

import com.example.togepi.exception.ResourceNotFoundException;
import com.example.togepi.model.User;
import com.example.togepi.service.OpenCSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OpenCSVController {

    @Autowired
    private OpenCSVService openCSVService;

    @GetMapping(value = "/csvs")
    public List<String[]> read() throws ResourceNotFoundException {
        return openCSVService.read();
    }

    @GetMapping(value = "/csvs/object")
    public List<User> readToObject() throws ResourceNotFoundException {
        return openCSVService.readToObject();
    }

    @PostMapping(value = "/csvs")
    public List<String[]> write() throws ResourceNotFoundException {
        return openCSVService.write();
    }

    @PostMapping(value = "/csvs/object")
    public List<User> writeFromObject() throws ResourceNotFoundException {
        return openCSVService.writeFromObject();
    }
}
