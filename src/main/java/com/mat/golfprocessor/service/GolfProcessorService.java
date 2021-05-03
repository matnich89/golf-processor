package com.mat.golfprocessor.service;

import com.mat.golfprocessor.convertor.Convertor;
import com.mat.golfprocessor.domain.dto.data.GolfData;
import com.mat.golfprocessor.domain.model.GolfTournament;
import com.mat.golfprocessor.exception.InvalidIdException;
import com.mat.golfprocessor.repository.GolfTournamentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GolfProcessorService {

    private final Map<Class<? extends GolfData>, Convertor> formatConvertorMap;

    private final GolfTournamentRepo golfTournamentRepo;

    public GolfTournament getById(final Long id) {
        return golfTournamentRepo.findById(id).orElseThrow(() -> new InvalidIdException(String.format("cannot find golf tournament with id %s", id)));
    }

    public void processData(final GolfData data) {
        GolfTournament golfTournament = formatConvertorMap.get(data.getClass()).apply(data);
        CompletableFuture.runAsync(() -> {
            saveRecord(golfTournament);
        });
    }

    /*
    This is invoked async so the source / client  is not sitting around waiting for the IO
    on the database to complete , this means any errors would trigger
    an event such as a notification or log an event to distributed logging
    for the purposes of simplicity just going to log but would be handled different in
    a prod env
     */
    private void saveRecord(final GolfTournament golfTournament) {
        try {
            golfTournamentRepo.save(golfTournament);
        } catch (Exception e) {
              log.error("Error occurred when persisting golf tournament with externalId {}", golfTournament.getExternalId());
        }

    }

}
