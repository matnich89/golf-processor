package com.mat.golfprocessor.repository;

import com.mat.golfprocessor.domain.model.GolfTournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GolfTournamentRepo extends JpaRepository<GolfTournament, Long> {
}
