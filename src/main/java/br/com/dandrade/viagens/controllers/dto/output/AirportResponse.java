package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.Airport;

public class AirportResponse {
    private final Long id;
    private final String name;
    private final String country;

    public AirportResponse(Airport airport) {
        this.id = airport.getId();
        this.name = airport.getName();
        this.country = airport.getCountryName();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
