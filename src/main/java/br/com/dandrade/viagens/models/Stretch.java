package br.com.dandrade.viagens.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Embeddable
public class Stretch implements Comparable<Stretch>{

    private Integer index;

    @NotNull
    @OneToOne
    private AirRoute airRoute;

    @Enumerated(EnumType.STRING)
    private FlightType type;

    @Positive
    private Long stopTime;

    public Stretch() {
    }

    public Stretch(Integer index, AirRoute airRoute, FlightType type, Long stopTime) {
        this.index = index;
        this.airRoute = airRoute;
        this.type = type;
        this.stopTime = stopTime;
    }

    public String getDescription() {
        return airRoute.getName()  + "(" + type.name().toLowerCase() + ")";
    }


    public AirRoute getAirRoute() {
        return airRoute;
    }

    public FlightType getType() {
        return type;
    }

    public Long getStopTime() {
        return stopTime;
    }

    public boolean isConnection() {
        return this.type == FlightType.CONNECTION;
    }

    public boolean isScale() {
        return this.type == FlightType.SCALE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stretch stretch = (Stretch) o;
        return airRoute.equals(stretch.airRoute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airRoute);
    }

    @Override
    public int compareTo(Stretch o) {
        return this.index.compareTo(o.index);
    }


    public LocalTime landingTime( LocalTime localTime, ChronoUnit chronoUnit ) {
        return localTime.plus( getAirRoute().getDuration(), chronoUnit );
    }

    public LocalTime takeOffTime( LocalTime localTime, ChronoUnit chronoUnit ) {
        return localTime.plus( stopTime, chronoUnit );
    }
}
