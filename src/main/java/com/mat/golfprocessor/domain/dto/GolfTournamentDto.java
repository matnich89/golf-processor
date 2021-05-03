package com.mat.golfprocessor.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor // modelmapper requires noargs constructor
public class GolfTournamentDto {
    private Long id;
    private String externalId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String country;
    private Integer rounds;
    private String source;
    private String course;
}
