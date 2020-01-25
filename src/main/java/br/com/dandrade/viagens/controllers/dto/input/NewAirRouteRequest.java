package br.com.dandrade.viagens.controllers.dto.input;

import br.com.dandrade.viagens.functions.FinderById;
import br.com.dandrade.viagens.models.AirRoute;
import br.com.dandrade.viagens.models.Airport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class NewAirRouteRequest {

    private String name;

    @NotNull
    private Long originAirportId;
    @NotNull
    private Long destinyAirportId;
    @Positive
    private Long duration;


    public void setName(String name) {
        this.name = name;
    }

    public void setOriginAirportId(Long originAirportId) {
        this.originAirportId = originAirportId;
    }

    public void setDestinyAirportId(Long destinyAirportId) {
        this.destinyAirportId = destinyAirportId;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "NewAirRouteRequest{" +
                "name=" + name +
                ", originAirportId=" + originAirportId +
                ", destinationAirportId=" + destinyAirportId +
                ", duration=" + duration +
                '}';
    }

    public AirRoute newAirRoute(FinderById<Long, Optional<Airport>> finderById) {
        Airport originAirport = finderById.findById(originAirportId).orElseThrow();
        Airport destinyAirport = finderById.findById(destinyAirportId).orElseThrow();
        return AirRoute.newBuilder()
                .withName(name)
                .withOrigin(originAirport)
                .withDestiny(destinyAirport)
                .withDuration(duration)
                .build();
    }
}
