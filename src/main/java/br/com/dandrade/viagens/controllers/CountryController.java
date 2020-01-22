package br.com.dandrade.viagens.controllers;

import br.com.dandrade.viagens.controllers.dto.input.CountryRequest;
import br.com.dandrade.viagens.controllers.dto.output.CountryResponse;
import br.com.dandrade.viagens.models.Country;
import br.com.dandrade.viagens.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryRepository.class);

    private CountryRepository repository;


    public CountryController(CountryRepository repository) {
        this.repository = repository;
    }

    @PostMapping()
    public CountryResponse add(@Valid @RequestBody CountryRequest request) {
        LOGGER.info("Country: {} ", request);
        Country country = request.newCountry();
        repository.save(country);
        return CountryResponse.create(country);
    }
}
