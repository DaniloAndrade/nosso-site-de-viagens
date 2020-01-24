package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.Company;

public class CompanyResponse {
    private final Long id;
    private final String name;
    private final String country;

    public CompanyResponse(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.country = company.getCountryName();
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
