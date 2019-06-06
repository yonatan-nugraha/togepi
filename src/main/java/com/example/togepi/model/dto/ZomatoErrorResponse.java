package com.example.togepi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties
@Data
public class ZomatoErrorResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;
}
