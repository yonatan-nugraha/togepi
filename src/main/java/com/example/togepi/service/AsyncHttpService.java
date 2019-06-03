package com.example.togepi.service;

import com.example.togepi.model.dto.ZomatoResponse;

import java.util.concurrent.CompletableFuture;

public interface AsyncHttpService {

    CompletableFuture<ZomatoResponse> getRestaurant();
}
