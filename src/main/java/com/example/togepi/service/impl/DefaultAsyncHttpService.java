package com.example.togepi.service.impl;

import com.example.togepi.model.dto.ZomatoErrorResponse;
import com.example.togepi.model.dto.ZomatoResponse;
import com.example.togepi.service.AsyncHttpService;
import com.example.togepi.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class DefaultAsyncHttpService implements AsyncHttpService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAsyncHttpService.class);
    private static final AsyncHttpClient httpClient = Dsl.asyncHttpClient();
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    private static final String USER_KEY = "a544f36cbd73b8f3da2aebe2575056ed";
    private static final String BASE_URL = "https://developers.zomato.com/api/v2.1";

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CompletableFuture<ZomatoResponse> getRestaurant() {
        final long start = System.currentTimeMillis();

        return httpClient.prepareGet(BASE_URL + "/restaurant?res_id=17842104")
                .addHeader("user-key", USER_KEY)
                .execute(new AsyncCompletionHandler<ZomatoResponse>() {
                    @Override
                    public ZomatoResponse onCompleted(Response response) throws Exception {
                        final String responseBody = response.getResponseBody();
                        final int statusCode = response.getStatusCode();
                        final HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
                        final boolean isSuccess = HttpStatus.OK.equals(httpStatus);

                        if (isSuccess) {
                            final ZomatoResponse zomatoResponse = objectMapper.readValue(responseBody, ZomatoResponse.class);
                            return zomatoResponse;
                        } else {
                            final ZomatoErrorResponse zomatoErrorResponse = objectMapper.readValue(responseBody, ZomatoErrorResponse.class);
                            final String message = zomatoErrorResponse.getMessage();
                            throw new ResourceNotFoundException(message);
                        }
                    }
                })
                .toCompletableFuture()
                .whenComplete((rsp, err) -> {
                    Optional.ofNullable(err).ifPresent(e -> {
                        e.printStackTrace();
                    });
                    final long end = System.currentTimeMillis();
                    final String duration = decimalFormat.format((end - start) / 1000.0f);
                    logger.info("Completed within {} seconds", duration);
                });
    }
}
