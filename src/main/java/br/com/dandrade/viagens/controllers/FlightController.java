package br.com.dandrade.viagens.controllers;

import br.com.dandrade.viagens.controllers.dto.input.NewFlightRequest;
import br.com.dandrade.viagens.controllers.dto.output.FlightResponse;
import br.com.dandrade.viagens.controllers.validators.NewCompanyValidator;
import br.com.dandrade.viagens.controllers.validators.NewFlightValidator;
import br.com.dandrade.viagens.models.Flight;
import br.com.dandrade.viagens.repository.AirRouteRepository;
import br.com.dandrade.viagens.repository.CompanyRepository;
import br.com.dandrade.viagens.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

    private FlightRepository flights;
    private AirRouteRepository airRoutes;
    private CompanyRepository companys;

    public FlightController(FlightRepository flights,
                            AirRouteRepository airRoutes,
                            CompanyRepository companys) {
        this.flights = flights;
        this.airRoutes = airRoutes;
        this.companys = companys;
    }


    @PostMapping
    public FlightResponse add(@Valid @RequestBody NewFlightRequest request) {
        LOGGER.info("New flight: {}", request);
        Flight flight = request.newFlight(airRoutes::findById,
                companys::findById);
        flights.save(flight);
        return new FlightResponse(flight);
    }

    @GetMapping
    public List<Flight> list() {
        return flights.findAll();
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(new NewFlightValidator());
    }
}
