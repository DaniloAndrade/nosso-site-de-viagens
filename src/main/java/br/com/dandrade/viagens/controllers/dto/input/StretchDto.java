package br.com.dandrade.viagens.controllers.dto.input;

import br.com.dandrade.viagens.functions.FinderById;
import br.com.dandrade.viagens.models.AirRoute;
import br.com.dandrade.viagens.models.FlightType;
import br.com.dandrade.viagens.models.Stretch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class StretchDto {

    private Integer index;

    @NotNull
    private Long airRouteId;

    private FlightType type;

    @Positive
    private Long stopTime;


    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setAirRouteId(Long airRouteId) {
        this.airRouteId = airRouteId;
    }

    public void setType(FlightType type) {
        this.type = type;
    }

    public void setStopTime(Long stopTime) {
        this.stopTime = stopTime;
    }

    @Override
    public String toString() {
        return "StretchDto{" +
                "order=" + index +
                ", airRoute=" + airRouteId +
                ", type='" + type + '\'' +
                ", stopTime=" + stopTime +
                '}';
    }

    Stretch newStretch(FinderById<Long, Optional<AirRoute>> finderById) {
        AirRoute airRoute = finderById.findById(this.airRouteId).orElseThrow();
        return new Stretch(index, airRoute, type, stopTime);
    }

    public boolean isDirect() {
        return type == FlightType.DIRECT;
    }
}
