package com.example.togepi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZomatoResponse {

    @JsonProperty("id")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("cuisines")
    private String cuisines;

    @JsonProperty("average_cost_for_two")
    private String avgCostForTwo;

    @JsonProperty("location")
    private Location location;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    private class Location {

        @JsonProperty("address")
        private String address;

        @JsonProperty("locality")
        private String locality;
    }
}
