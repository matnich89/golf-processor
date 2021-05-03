package com.mat.golfprocessor.domain.dto.data;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use= JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = GolfDataSource1.class),
        @JsonSubTypes.Type(value = GolfDataSource2.class)
})
public interface GolfData {

}
