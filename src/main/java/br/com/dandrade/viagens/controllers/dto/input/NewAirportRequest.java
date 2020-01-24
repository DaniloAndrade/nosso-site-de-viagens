package br.com.dandrade.viagens.controllers.dto.input;

import br.com.dandrade.viagens.functions.FinderById;
import br.com.dandrade.viagens.models.Airport;
import br.com.dandrade.viagens.models.Country;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class NewAirportRequest {

    @NotBlank
    private String name;
    @NotNull
    private Long countryId;

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return this.name;
    }

    public Airport newAirport(FinderById<Long, Optional<Country>> finderById) {
        return finderById.findById(countryId).map(c -> new Airport(name, c)).orElseThrow();
    }

    @Override
    public String toString() {
        return "NewAirportRequest{" +
                "name='" + name + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
