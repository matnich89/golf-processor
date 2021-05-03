package com.mat.golfprocessor.util;

import com.mat.golfprocessor.domain.dto.data.GolfData;
import com.mat.golfprocessor.domain.dto.data.GolfDataSource1;
import com.mat.golfprocessor.domain.dto.data.GolfDataSource2;

import java.time.LocalDate;

public class GolfDataProviderUtil {

    public static GolfData createGolfDataSource1() {
        return buildGolfDataSource1();
    }

    public static GolfData createGolfDataSource2() {
        return buildGolfDataSource2();
    }

    public static GolfData createGolfDataSource1BadDates() {
        return buildGolfDataSource1BadDates();
    }

    public static GolfData createGolfDataSource2BadDates() {
        return buildGolfDataSource2BadDates();
    }

    public static GolfDataSource1 createConcreteGolfDataSource1() {
        return buildGolfDataSource1();
    }

    public static GolfDataSource2 createConcreteGolfDataSource2() {
        return buildGolfDataSource2();
    }

    private static GolfDataSource1 buildGolfDataSource1() {

        return GolfDataSource1.builder()
                .tournamentId(1234L)
                .courseName("Mat1")
                .countryCode("GB")
                .startDate(LocalDate.now().plusDays(10))
                .endDate(LocalDate.now().plusDays(12))
                .roundCount(12)
                .build();

    }

    private static GolfDataSource1 buildGolfDataSource1BadDates() {

        return GolfDataSource1.builder()
                .tournamentId(1234L)
                .courseName("Mat1")
                .countryCode("GB")
                .startDate(LocalDate.now().plusDays(12))
                .endDate(LocalDate.now().plusDays(10))
                .roundCount(12)
                .build();
    }


    private static GolfDataSource2 buildGolfDataSource2() {

        return GolfDataSource2.builder()
                .tournamentUUID("matsTournament123")
                .golfCourse("Mat2")
                .hostCountry("United Kingdom")
                .epochStart(1633197021L)
                .epochFinish(1633456221L)
                .rounds(5)
                .playerCount(12).build();

    }

    private static GolfDataSource2 buildGolfDataSource2BadDates() {
        return GolfDataSource2.builder()
                .tournamentUUID("matsTournament123")
                .golfCourse("Mat2")
                .hostCountry("United Kingdom")
                .epochStart(1633456221L)
                .epochFinish(1633197021L)
                .rounds(5)
                .playerCount(12).build();
    }

}
