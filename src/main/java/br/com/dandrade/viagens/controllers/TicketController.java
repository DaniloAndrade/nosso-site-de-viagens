package br.com.dandrade.viagens.controllers;

import br.com.dandrade.viagens.controllers.dto.input.NewTicketRequest;
import br.com.dandrade.viagens.controllers.dto.output.TicketResponse;
import br.com.dandrade.viagens.models.Ticket;
import br.com.dandrade.viagens.repository.FlightRepository;
import br.com.dandrade.viagens.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
