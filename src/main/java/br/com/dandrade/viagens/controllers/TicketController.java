package br.com.dandrade.viagens.controllers;

import br.com.dandrade.viagens.controllers.dto.input.NewTicketRequest;
import br.com.dandrade.viagens.controllers.dto.input.TicketFilter;
import br.com.dandrade.viagens.controllers.dto.output.TicketResponse;
import br.com.dandrade.viagens.controllers.dto.output.TicketSummary;
import br.com.dandrade.viagens.models.Ticket;
import br.com.dandrade.viagens.repository.FlightRepository;
import br.com.dandrade.viagens.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    private TicketRepository tickets;
    private FlightRepository flights;

    public TicketController(TicketRepository tickets,
                            FlightRepository flights) {
        this.tickets = tickets;
        this.flights = flights;
    }

    @PostMapping
    public TicketResponse add(@RequestBody @Valid NewTicketRequest request) {
        LOGGER.info("New Ticket: {}", request);
        Ticket ticket = request.newTicket(flights::findById);
        tickets.save(ticket);
        return new TicketResponse(ticket);
    }

    @GetMapping
    public List<TicketSummary> find(@Valid TicketFilter filter) {
        if (filter.isRoundTrip()) {
            List<Ticket> outwardTickets = tickets.findAll( filter.outwardFilter() );
            List<Ticket> backTickets = tickets.findAll( filter.backFilter() );
            return TicketSummary.create( outwardTickets, backTickets, filter.getNumberOfPassengers() );
        }
        return TicketSummary.create( tickets.findAll( filter.outwardFilter() ), filter.getNumberOfPassengers());
    }
}
