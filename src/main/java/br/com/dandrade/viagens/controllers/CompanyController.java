package br.com.dandrade.viagens.controllers;

import br.com.dandrade.viagens.controllers.dto.input.CompanyRequest;
import br.com.dandrade.viagens.controllers.dto.output.CompanyResponse;
import br.com.dandrade.viagens.controllers.validators.CompanyValidator;
import br.com.dandrade.viagens.models.Company;
import br.com.dandrade.viagens.repository.CompanyRepository;
import br.com.dandrade.viagens.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);


    private CompanyRepository companyRepository;
    private CountryRepository countryRepository;

    public CompanyController(CompanyRepository companyRepository,
                             CountryRepository countryRepository) {
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
    }

    @PostMapping
    public CompanyResponse add(@Valid @RequestBody CompanyRequest request) {
        Company company = request.newCompany(countryRepository::findByName);
        companyRepository.save(company);
        return CompanyResponse.create(company);
    }

    @InitBinder
    public void initBinde(WebDataBinder dataBinder) {
        dataBinder.addValidators(new CompanyValidator(companyRepository, countryRepository));
    }


}
