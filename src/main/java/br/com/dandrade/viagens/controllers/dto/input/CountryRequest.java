package br.com.dandrade.viagens.controllers.dto.input;

import br.com.dandrade.viagens.models.Country;

import javax.validation.constraints.NotBlank;

public class CountryRequest {

    @NotBlank
    private String name;

    public void setName(String name) {
        this.name = name;
    }


    public Country newCountry() {
        return new Country(this.name);
    }
}
