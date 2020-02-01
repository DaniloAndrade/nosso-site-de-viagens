package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.Ticket;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class TicketSummary {

    private String company;
    private String origin;
    private String destiny;
    private List<String> connectionsOrScale;
    private BigDecimal totalValue;


    public TicketSummary(Ticket ticket, Integer numberOfPassengers) {
        this.company = ticket.getCompanyName();
        this.destiny = ticket.getDestiny();
        this.origin = ticket.getOrigin();
        this.connectionsOrScale = ticket.getStretchs()
                .stream()
                .filter( stretch -> stretch.isConnection() || stretch.isScale() )
                .map( stretch ->
                              stretch.getAirRoute()
                                      .getDestinyAirportName() + "(" + stretch.getType().name().toLowerCase() + ")"
                ).collect( Collectors.toList());

        this.totalValue = ticket.getValue().multiply(new BigDecimal( numberOfPassengers ) );
    }


    public String getCompany() {
        return company;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public List<String> getConnectionsOrScale() {
        return connectionsOrScale;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public static List<TicketSummary> create( List<Ticket> tickets, final Integer numberOfPassengers ) {
        return tickets.stream().map(ticket -> new TicketSummary(ticket, numberOfPassengers))
                .collect( Collectors.toList() );
    }
}
