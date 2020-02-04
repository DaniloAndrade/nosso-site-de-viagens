package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.Stretch;
import br.com.dandrade.viagens.models.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class FlightDto {
    private final String company;
    private final String destiny;
    private final String origin;
    private final List<StretchDto> stretchs;
    private final List<String> connectionsOrScale;
    private final Long totalTimeOfScale;

    public FlightDto( Ticket ticket ) {
        this.company = ticket.getCompanyName();
        this.destiny = ticket.getDestiny();
        this.origin = ticket.getOrigin();
        this.stretchs = StretchDto.create(ticket.getStretchs(), ticket.getDeparture());
        this.totalTimeOfScale = calculateDowntime( ticket );
        this.connectionsOrScale = retrieveScalesOrConnections( ticket );
    }


    public String getCompany() {
        return company;
    }

    public String getDestiny() {
        return destiny;
    }

    public String getOrigin() {
        return origin;
    }

    public List<StretchDto> getStretchs() {
        return stretchs;
    }

    public List<String> getConnectionsOrScale() {
        return connectionsOrScale;
    }

    public Long getTotalTimeOfScale() {
        return totalTimeOfScale;
    }

    private List<String> retrieveScalesOrConnections( Ticket ticket ) {
        return ticket.getStretchs()
                .stream()
                .filter( stretch -> stretch.isConnection() || stretch.isScale() )
                .map( stretch ->
                              stretch.getAirRoute()
                                      .getDestinyAirportName() + "(" + stretch.getType().name().toLowerCase() + ")"
                ).collect( Collectors.toList());
    }

    private Long calculateDowntime( Ticket ticket ) {
        return ticket.getStretchs()
                .stream().filter( stretch -> stretch.isConnection() || stretch.isScale() )
                .map( Stretch::getStopTime ).reduce( 0L, Long::sum );
    }
}
