package com.mat.golfprocessor.domain.dto.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GolfDataSource2 implements GolfData {
    @JsonProperty(required = true)
    String tournamentUUID;
    @JsonProperty(required = true)
    String golfCourse;
    @JsonProperty(required = true)
    String competitionName;
    @JsonProperty(required = true)
    String hostCountry;
    @JsonProperty(required = true)
    Long epochStart;
    @JsonProperty(required = true)
    Long epochFinish;
    @JsonProperty(required = true)
    Integer rounds;
    @JsonProperty(required = true)
    Integer playerCount;
}
