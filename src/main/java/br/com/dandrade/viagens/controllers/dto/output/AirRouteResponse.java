package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.AirRoute;

public class AirRouteResponse {

    private final Long id;
    private final String name;
    private final String destinyAirport;
    private final String originAirport;
    private final long duration;


    public AirRouteResponse(AirRoute airRoute) {
        id = airRoute.getId();
        name = airRoute.getName();
        originAirport = airRoute.getOriginAirportName();
        destinyAirport = airRoute.getDestinyAirportName();
        duration = airRoute.getDuration();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDestinyAirport() {
        return destinyAirport;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public long getDuration() {
        return duration;
    }
}
