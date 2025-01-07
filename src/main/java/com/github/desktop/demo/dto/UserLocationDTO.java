package com.github.desktop.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserLocationDTO {
    @JsonProperty("street")
    private UserLocationStreetDTO streetDTO;
    private String city;
    private String state;
    private String country;
    @JsonProperty("postcode")
    private String postCode;
    @JsonProperty("coordinates")
    private UserLocationCoordinateDTO coordinateDTO;
    @JsonProperty("timezone")
    private UserLocationTimezoneDTO timezoneDTO;
}
