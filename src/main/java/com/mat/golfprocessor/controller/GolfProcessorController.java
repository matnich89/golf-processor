package com.mat.golfprocessor.controller;

import com.mat.golfprocessor.domain.dto.GolfTournamentDto;
import com.mat.golfprocessor.domain.dto.data.GolfData;
import com.mat.golfprocessor.service.GolfProcessorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/data/golf")
@RequiredArgsConstructor
public class GolfProcessorController {

    private final GolfProcessorService processorService;

    private final ModelMapper modelMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> processSource(
            final @RequestBody @Valid GolfData data) {
        processorService.processData(data);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GolfTournamentDto> getTournamentById(final @PathVariable Long id) {
        return ResponseEntity.ok(modelMapper.map(processorService.getById(id), GolfTournamentDto.class));
    }

}
