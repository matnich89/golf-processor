package com.mat.golfprocessor.convertor;

import com.mat.golfprocessor.domain.dto.data.GolfData;
import com.mat.golfprocessor.domain.dto.data.GolfDataSource2;
import com.mat.golfprocessor.domain.enums.Source;
import com.mat.golfprocessor.domain.model.GolfTournament;
import util.DateUtil;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Source2Convertor implements Convertor {


    @Override
    public GolfTournament apply(final GolfData golfData) {
        final ZoneId zoneId = ZoneId.of("UTC");
        final GolfDataSource2 formattedData = (GolfDataSource2) golfData;
        final GolfTournament golfTournament = new GolfTournament();
        final LocalDate startDate = Instant.ofEpochSecond(formattedData.getEpochStart()).atZone(zoneId).toLocalDate();
        final LocalDate endDate = Instant.ofEpochSecond(formattedData.getEpochFinish()).atZone(zoneId).toLocalDate();
        DateUtil.validateDates(startDate,endDate);
        golfTournament.setExternalId(formattedData.getTournamentUUID());
        golfTournament.setStartDate(startDate);
        golfTournament.setEndDate(endDate);
        golfTournament.setSource(Source.SOURCE2.getValue());
        golfTournament.setRounds(formattedData.getRounds());
        golfTournament.setCourse(formattedData.getGolfCourse());
        golfTournament.setCountry(formattedData.getHostCountry());
        return golfTournament;
    }

}
