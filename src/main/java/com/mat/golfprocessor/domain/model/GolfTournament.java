package com.mat.golfprocessor.domain.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/*
Have not used lombok you shouldn't use Lombok with entity
See https://thorben-janssen.com/lombok-hibernate-how-to-avoid-common-pitfalls/
 */
@Entity
@Table(name = "golf_tournament")
public class GolfTournament {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String externalId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String country;
    private Integer rounds;
    private String source;
    private String course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getRounds() {
        return rounds;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GolfTournament that = (GolfTournament) o;
        return Objects.equals(id, that.id) && Objects.equals(externalId, that.externalId) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(country, that.country) && Objects.equals(rounds, that.rounds) && Objects.equals(source, that.source) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, externalId, startDate, endDate, country, rounds, source, course);
    }
}
