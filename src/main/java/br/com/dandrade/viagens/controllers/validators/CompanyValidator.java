package br.com.dandrade.viagens.controllers.validators;

import br.com.dandrade.viagens.controllers.dto.input.CompanyRequest;
import br.com.dandrade.viagens.repository.CompanyRepository;
import br.com.dandrade.viagens.repository.CountryRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CompanyValidator implements Validator {

    private CompanyRepository companyRepository;
    private CountryRepository countryRepository;

    public CompanyValidator(CompanyRepository companyRepository,
                            CountryRepository countryRepository) {
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CompanyRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CompanyRequest request = (CompanyRequest) o;
        boolean existsCompany = this.companyRepository.existsByName(request.getName());
        if (existsCompany){
            errors.reject("company.name.already.exists",
                    new Object[]{request.getName()},
                    "Já existe companhia com o nome informado");
        }

        boolean existsCountry = this.countryRepository.existsByName(request.getCountry());
        if (!existsCountry) {
            errors.reject("company.country.not.found",
                    new Object[]{request.getCountry()},
                    "País informado não encontrado");
        }

    }
}
