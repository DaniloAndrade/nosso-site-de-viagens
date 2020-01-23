package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.Company;

public class CompanyResponse {
    private final Long id;
    private final String name;
    private final String country;

    public CompanyResponse(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public static CompanyResponse create(Company company) {
        return new CompanyResponse(company.getId(), company.getName(),
                company.getCountryName() );
    }
}
