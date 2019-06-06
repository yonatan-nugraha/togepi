package com.example.togepi.service.impl;

import com.example.togepi.service.MainService;
import org.springframework.stereotype.Service;

@Service("defaultMainService")
public class DefaultMainService implements MainService {

    @Override
    public String hello() {
        return "hello default";
    }
}
