package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.Flight;
import br.com.dandrade.viagens.models.Stretch;

import java.util.stream.Collectors;

public class FlightResponse {

    private final Long id;
    private final Integer numberOfSeats;
    private final String stretchs;

    public FlightResponse(Flight flight) {
        id = flight.getId();
        numberOfSeats = flight.getNumberOfSeats();
        stretchs = flight.getStretchs().stream()
                .map(Stretch::getDescription)
                .collect(Collectors.joining(","));
    }


    public Long getId() {
        return id;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getStretchs() {
        return stretchs;
    }
}
