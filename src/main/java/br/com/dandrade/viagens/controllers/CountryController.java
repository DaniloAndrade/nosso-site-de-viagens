package br.com.dandrade.viagens.controllers;

import br.com.dandrade.viagens.models.Country;
import br.com.dandrade.viagens.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/country",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryRepository.class);

    private CountryRepository repository;


    public CountryController(CountryRepository repository) {
        this.repository = repository;
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public Country add(@Valid @RequestBody Country country) {
        LOGGER.info("Country: {} ", country);
        repository.save(country);
        return country;
    }
}
