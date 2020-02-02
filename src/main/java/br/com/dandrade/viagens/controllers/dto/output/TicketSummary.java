package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.Stretch;
import br.com.dandrade.viagens.models.Ticket;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class TicketSummary {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern( "HH:mm" );

    private Long totalTimeOfScale;
    private String stretchHours;
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

        StringBuilder hours = new StringBuilder(  );
        LocalTime stretchTime = ticket.getDeparture().toLocalTime();
        Long timeOfScale = 0L;

        for (Stretch stretch: ticket.getStretchs()) {
            hours.append( "(out) " ).append( stretch.getAirRoute().getOriginAirportName());
            hours.append( "(" ).append( stretchTime.format( TIME_FORMATTER )).append( ") - " );
            stretchTime = stretchTime.plus( stretch.getAirRoute().getDuration(), ChronoUnit.MINUTES );
            hours.append( "(in) " ).append( stretch.getAirRoute().getDestinyAirportName() );
            hours.append( "(" ).append( stretchTime.format( TIME_FORMATTER )).append( ")" );
            stretchTime = stretchTime.plus( stretch.getStopTime(), ChronoUnit.MINUTES );

            if (stretch.isScale() || stretch.isConnection()) {
                hours.append( ", " );
                timeOfScale += stretch.getStopTime();
            }

        }
        this.totalTimeOfScale = timeOfScale;
        this.stretchHours = hours.toString();


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

    public String getStretchHours() {
        return stretchHours;
    }

    public Long getTotalTimeOfScale() {
        return totalTimeOfScale;
    }

    public static List<TicketSummary> create( List<Ticket> tickets, final Integer numberOfPassengers ) {
        return tickets.stream().map(ticket -> new TicketSummary(ticket, numberOfPassengers))
                .collect( Collectors.toList() );
    }
}
