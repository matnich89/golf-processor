package com.mat.golfprocessor.service;

import com.mat.golfprocessor.convertor.Convertor;
import com.mat.golfprocessor.convertor.Source1Convertor;
import com.mat.golfprocessor.convertor.Source2Convertor;
import com.mat.golfprocessor.domain.dto.data.GolfData;
import com.mat.golfprocessor.domain.dto.data.GolfDataSource1;
import com.mat.golfprocessor.domain.dto.data.GolfDataSource2;
import com.mat.golfprocessor.domain.model.GolfTournament;
import com.mat.golfprocessor.repository.GolfTournamentRepo;
import com.mat.golfprocessor.util.GolfDataProviderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GolfProcessorServiceTest {

    @Mock
    private Map<Class<? extends GolfData>, Convertor> formatConvertorMap;

    @Mock
    private GolfTournamentRepo golfTournamentRepo;

    @Mock
    private Source1Convertor source1Convertor;

    @Mock
    private Source2Convertor source2Convertor;

    private GolfProcessorService golfProcessorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.golfProcessorService = new GolfProcessorService(formatConvertorMap, golfTournamentRepo);
    }

    @Test
    public void shouldProcessSource1DataCorrectly() {
        doReturn(new GolfTournament()).when(source1Convertor).apply(any());
        doReturn(source1Convertor).when(formatConvertorMap).get(GolfDataSource1.class);
        final GolfData golfData = GolfDataProviderUtil.createGolfDataSource1();
        golfProcessorService.processData(golfData);
        verify(source1Convertor).apply(golfData);
        verify(golfTournamentRepo).save(any(GolfTournament.class));
    }

    @Test
    public void shouldProcessSource2DataCorrectly() {
        doReturn(new GolfTournament()).when(source2Convertor).apply(any());
        doReturn(source2Convertor).when(formatConvertorMap).get(GolfDataSource2.class);
        final GolfData golfData = GolfDataProviderUtil.createGolfDataSource2();
        golfProcessorService.processData(golfData);
        verify(source2Convertor).apply(golfData);
        verify(golfTournamentRepo).save(any(GolfTournament.class));
    }


}
