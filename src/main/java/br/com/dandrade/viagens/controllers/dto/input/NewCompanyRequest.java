package br.com.dandrade.viagens.controllers.dto.input;

import br.com.dandrade.viagens.functions.FinderById;
import br.com.dandrade.viagens.models.Company;
import br.com.dandrade.viagens.models.Country;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

public class NewCompanyRequest {

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

    public Long getCountryId() {
        return countryId;
    }

    public String getName() {
        return this.name;
    }

    public Company newCompany(FinderById<Long, Optional<Country>> findCountry) {
        return findCountry.findById(countryId).map(c -> new Company(name, c)).orElseThrow();
    }

    @Override
    public String toString() {
        return "NewCompanyRequest{" +
                "name='" + name + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
