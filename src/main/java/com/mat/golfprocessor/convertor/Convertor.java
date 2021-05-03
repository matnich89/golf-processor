package com.mat.golfprocessor.convertor;

import com.mat.golfprocessor.domain.dto.data.GolfData;
import com.mat.golfprocessor.domain.model.GolfTournament;

import java.util.function.Function;

public interface Convertor extends Function<GolfData, GolfTournament> {
}
