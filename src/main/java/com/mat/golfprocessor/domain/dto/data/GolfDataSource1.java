package com.mat.golfprocessor.domain.dto.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GolfDataSource1 implements GolfData {
    @JsonProperty(required = true)
    Long tournamentId;
    @JsonProperty(required = true)
    String tournamentName;
    @JsonProperty(required = true)
    String courseName;
    @JsonProperty(required = true)
    String countryCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy")
    @JsonProperty(required = true)
    LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy")
    @JsonProperty(required = true)
    LocalDate endDate;
    @JsonProperty(required = true)
    Integer roundCount;
}