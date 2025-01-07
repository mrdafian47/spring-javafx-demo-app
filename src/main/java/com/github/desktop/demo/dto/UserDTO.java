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
public class UserDTO {
    @JsonProperty("name")
    private UserNameDTO nameDTO;
    @JsonProperty("location")
    private UserLocationDTO locationDTO;
    private String gender;
    private String email;
    private String phone;
    private String cell;
    @JsonProperty("picture")
    private UserPictureDTO pictureDTO;
    private String nat;
}
