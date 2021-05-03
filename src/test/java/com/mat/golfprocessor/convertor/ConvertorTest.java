package com.mat.golfprocessor.convertor;


import com.mat.golfprocessor.domain.dto.data.GolfData;
import com.mat.golfprocessor.domain.dto.data.GolfDataSource1;
import com.mat.golfprocessor.domain.dto.data.GolfDataSource2;
import com.mat.golfprocessor.domain.enums.Source;
import com.mat.golfprocessor.domain.model.GolfTournament;
import com.mat.golfprocessor.exception.convertor.DateMismatchException;
import com.mat.golfprocessor.util.GolfDataProviderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ConvertorTest {

    private final Source1Convertor source1Convertor = new Source1Convertor();
    private final Source2Convertor source2Convertor = new Source2Convertor();
    private final ZoneId zoneId = ZoneId.of("UTC");

    @Test
    public void shouldConvertFromSource1Data() {
        final GolfDataSource1 golfData = GolfDataProviderUtil.createConcreteGolfDataSource1();
        final GolfTournament golfTournament = source1Convertor.apply(golfData);
        assertThat(golfTournament.getCountry().equals(golfData.getCountryCode())).isTrue();
        assertThat(golfTournament.getCourse().equals(golfData.getCourseName())).isTrue();
        assertThat(golfTournament.getSource().equals(Source.SOURCE1.getValue())).isTrue();
        assertThat(golfTournament.getStartDate().equals(golfData.getStartDate())).isTrue();
        assertThat(golfTournament.getEndDate().equals(golfData.getEndDate())).isTrue();
        assertThat(golfTournament.getExternalId().equals(String.valueOf(golfData.getTournamentId()))).isTrue();
        assertThat(golfTournament.getRounds().equals(golfData.getRoundCount())).isTrue();
    }

    @Test
    public void shouldConvertFromSource2Data() {
        final GolfDataSource2 golfData = GolfDataProviderUtil.createConcreteGolfDataSource2();
        final GolfTournament golfTournament = source2Convertor.apply(golfData);
        assertThat(golfTournament.getCountry().equals(golfData.getHostCountry())).isTrue();
        assertThat(golfTournament.getCourse().equals(golfData.getGolfCourse())).isTrue();
        assertThat(golfTournament.getSource().equals(Source.SOURCE2.getValue())).isTrue();
        assertThat(golfTournament.getStartDate().equals(Instant.ofEpochSecond(golfData.getEpochStart()).atZone(zoneId).toLocalDate())).isTrue();
        assertThat(golfTournament.getEndDate().equals(Instant.ofEpochSecond(golfData.getEpochFinish()).atZone(zoneId).toLocalDate())).isTrue();
        assertThat(golfTournament.getExternalId().equals(String.valueOf(golfData.getTournamentUUID()))).isTrue();
        assertThat(golfTournament.getRounds().equals(golfData.getRounds())).isTrue();
    }

    @Test
    public void shouldHandleSource1DataBadDates() {
        final GolfData golfData =  GolfDataProviderUtil.createGolfDataSource1BadDates();
        assertThrows(DateMismatchException.class, () -> source1Convertor.apply(golfData));
    }

    @Test
    public void shouldHandleSource2DataBadDates() {
        final GolfData golfData =  GolfDataProviderUtil.createGolfDataSource2BadDates();
        assertThrows(DateMismatchException.class, () -> source2Convertor.apply(golfData));
    }
}
