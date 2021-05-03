package com.mat.golfprocessor.domain.enums;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum Source {

    SOURCE1("source1"),
    SOURCE2("source2");

    private final String value;

    public String getValue() {
        return value;
    }
}
