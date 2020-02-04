package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.Ticket;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class TicketSummary {

    private FlightDto outward;
    @JsonInclude(Include.NON_NULL)
    private FlightDto back;
    private BigDecimal totalValue;


    public TicketSummary(Ticket ticket, Integer numberOfPassengers) {
        this.outward = new FlightDto( ticket );
        this.totalValue = ticket.getValue().multiply(new BigDecimal( numberOfPassengers ) );

    }

    public TicketSummary( Ticket outward, Ticket back, Integer numberOfPassengers ) {
        this.outward = new FlightDto( outward);
        this.back = new FlightDto( back);
        this.totalValue = outward.getValue()
                .add( back.getValue() )
                .multiply( new BigDecimal( numberOfPassengers ) );
    }

    public FlightDto getOutward() {
        return outward;
    }

    public FlightDto getBack() {
        return back;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public static List<TicketSummary> create( List<Ticket> outwardTickets, List<Ticket> backTickets,
            Integer numberOfPassengers ) {
        return outwardTickets.stream()
                .flatMap( i ->  backTickets.stream().map( v -> new TicketSummary( i, v, numberOfPassengers ) ))
                .collect( Collectors.toList() );
    }

    public static List<TicketSummary> create( List<Ticket> tickets, final Integer numberOfPassengers ) {
        return tickets.stream().map(ticket -> new TicketSummary(ticket, numberOfPassengers))
                .collect( Collectors.toList() );
    }
}
