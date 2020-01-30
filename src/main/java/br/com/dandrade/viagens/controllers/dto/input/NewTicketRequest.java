package br.com.dandrade.viagens.controllers.dto.input;

import br.com.dandrade.viagens.functions.FinderById;
import br.com.dandrade.viagens.models.Flight;
import br.com.dandrade.viagens.models.Ticket;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class NewTicketRequest {

    private @NotNull Long flightId;

    private @Future LocalDateTime departure;

    private @NotNull @Positive BigDecimal value;

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Ticket newTicket(FinderById<Long, Optional<Flight>> finderById) {
        Flight flight = finderById.findById(flightId).orElseThrow();
        return new Ticket(flight, departure, value);
    }

    @Override
    public String toString() {
        return "NewTicketRequest{" +
                "flightId=" + flightId +
                ", departure=" + departure +
                ", value=" + value +
                '}';
    }
}
