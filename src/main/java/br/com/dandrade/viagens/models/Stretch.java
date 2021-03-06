package br.com.dandrade.viagens.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
}
