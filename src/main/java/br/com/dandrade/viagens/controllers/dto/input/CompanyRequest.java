package br.com.dandrade.viagens.controllers.dto.input;

import br.com.dandrade.viagens.models.Company;
import br.com.dandrade.viagens.models.Country;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.function.Function;

public class CompanyRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String country;

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return this.name;
    }

    public Company newCompany(Function<String, Optional<Country>> findCountry) {
        return findCountry.apply(country).map(c -> new Company(name, c)).orElseThrow();
    }
}
