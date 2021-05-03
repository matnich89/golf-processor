package com.mat.golfprocessor.convertor;

import com.mat.golfprocessor.domain.dto.data.GolfData;
import com.mat.golfprocessor.domain.dto.data.GolfDataSource1;
import com.mat.golfprocessor.domain.enums.Source;
import com.mat.golfprocessor.domain.model.GolfTournament;
import util.DateUtil;

public class Source1Convertor implements Convertor {

    @Override
    public GolfTournament apply(final GolfData golfData) {
        final GolfDataSource1 formattedData = (GolfDataSource1) golfData;
        final GolfTournament golfTournament = new GolfTournament();
        DateUtil.validateDates(formattedData.getStartDate(), formattedData.getEndDate());
        golfTournament.setCountry(formattedData.getCountryCode());
        golfTournament.setSource(Source.SOURCE1.getValue());
        golfTournament.setStartDate(formattedData.getStartDate());
        golfTournament.setEndDate(formattedData.getEndDate());
        golfTournament.setRounds(formattedData.getRoundCount());
        golfTournament.setExternalId(String.valueOf(formattedData.getTournamentId()));
        golfTournament.setCourse(formattedData.getCourseName());
        return golfTournament;
    }
}