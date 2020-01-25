package br.com.dandrade.viagens.controllers;

import br.com.dandrade.viagens.controllers.dto.input.NewAirRouteRequest;
import br.com.dandrade.viagens.controllers.dto.output.AirRouteResponse;
import br.com.dandrade.viagens.models.AirRoute;
import br.com.dandrade.viagens.repository.AirRouteRepository;
import br.com.dandrade.viagens.repository.AirportRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/air-route")
public class AirRouteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirportController.class);

    private AirRouteRepository airRouteRepository;
    private AirportRespository airportRespository;

    public AirRouteController(AirRouteRepository airRouteRepository,
                              AirportRespository airportRespository) {
        this.airRouteRepository = airRouteRepository;
        this.airportRespository = airportRespository;
    }

    @PostMapping
    public AirRouteResponse add(@Valid @RequestBody NewAirRouteRequest request) {
        LOGGER.info("New air route: {}", request);
        AirRoute airRoute = request.newAirRoute(airportRespository::findById);
        airRouteRepository.save(airRoute);
        return new AirRouteResponse(airRoute);
    }


}
