package com.example.togepi.service.impl;

import com.example.togepi.service.MainService;
import org.springframework.stereotype.Service;

@Service("secondMainService")
public class SecondMainService implements MainService {

    @Override
    public String hello() {
        return "hello second";
    }
}
