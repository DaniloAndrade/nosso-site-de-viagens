package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.Country;

public class CountryResponse {

    private Long id;
    private String name;

    CountryResponse(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public static CountryResponse create(Country country) {
        return new CountryResponse(country.getName(), country.getId());
    }


}
