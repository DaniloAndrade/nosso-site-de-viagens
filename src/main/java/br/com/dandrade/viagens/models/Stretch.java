package br.com.dandrade.viagens.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class Stretch {

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
}
