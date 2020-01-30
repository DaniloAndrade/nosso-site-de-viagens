package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketResponse {

    private final Long id;
    private final LocalDateTime departure;
    private final BigDecimal value;
    private final String stretchs;
    private final String company;

    public TicketResponse(Ticket ticket) {
        id = ticket.getId();
        departure = ticket.getDeparture();
        value = ticket.getValue();
        stretchs = ticket.getFlightStretchs();
        company = ticket.getCompanyName();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getStretchs() {
        return stretchs;
    }

    public String getCompany() {
        return company;
    }
}
