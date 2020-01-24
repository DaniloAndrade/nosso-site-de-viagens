package br.com.dandrade.viagens.controllers;

import br.com.dandrade.viagens.controllers.dto.input.NewAirportRequest;
import br.com.dandrade.viagens.controllers.dto.output.AirportResponse;
import br.com.dandrade.viagens.controllers.validators.NewAirportValidator;
import br.com.dandrade.viagens.models.Airport;
import br.com.dandrade.viagens.repository.AirportRespository;
import br.com.dandrade.viagens.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/airport")
public class AirportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirportController.class);

    private AirportRespository airports;
    private CountryRepository countrys;

    public AirportController(AirportRespository airports,
                             CountryRepository countrys) {
        this.airports = airports;
        this.countrys = countrys;
    }

    @PostMapping
    public AirportResponse add(@Valid @RequestBody NewAirportRequest request) {
        LOGGER.info("New airport: {}", request);
        Airport airport = request.newAirport(countrys::findById);
        airports.save(airport);
        return new AirportResponse(airport);
    }

    @InitBinder
    public void initBinde(WebDataBinder dataBinder) {
        dataBinder.addValidators(new NewAirportValidator(airports));
    }
}
