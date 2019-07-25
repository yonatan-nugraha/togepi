package com.example.togepi.service;

import com.example.togepi.exception.ResourceNotFoundException;
import com.example.togepi.entity.User;

import java.util.List;

public interface OpenCsvService {

    List<String[]> read() throws ResourceNotFoundException;

    List<User> readToObject() throws ResourceNotFoundException;

    List<String[]> write() throws ResourceNotFoundException;

    List<User> writeFromObject() throws ResourceNotFoundException;
}
