package br.com.dandrade.viagens.models;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Flight flight;

    @Future
    private LocalDateTime departure;

    @NotNull
    @Positive
    private BigDecimal value;

    public Ticket() {
    }

    public Ticket(Flight flight, LocalDateTime departure, BigDecimal value) {
        this.flight = flight;
        this.departure = departure;
        this.value = value;
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

    public String getFlightStretchs() {
        return this.flight.getStretchs()
                .stream()
                .map(Stretch::getDescription)
                .collect(Collectors.joining(","));
    }

    public String getCompanyName() {
        return flight.getCompanyName();
    }
}
