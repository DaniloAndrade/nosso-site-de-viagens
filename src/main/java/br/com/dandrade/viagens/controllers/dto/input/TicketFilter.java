package br.com.dandrade.viagens.controllers.dto.input;

import br.com.dandrade.viagens.models.Ticket;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketFilter {

    private boolean roundTrip;
    @NotNull
    private Long airportOfOriginId;
    @NotNull
    private Long airportOfDestinyId;
    @NotNull
    private LocalDate departureDate;
    private LocalDate returnDate;
    @Positive
    private Integer numberOfPassengers;
    private BigDecimal maxValue;

    public void setRoundTrip( boolean roundTrip ) {
        this.roundTrip = roundTrip;
    }

    public void setAirportOfOriginId( Long airportOfOriginId ) {
        this.airportOfOriginId = airportOfOriginId;
    }

    public void setAirportOfDestinyId( Long airportOfDestinyId ) {
        this.airportOfDestinyId = airportOfDestinyId;
    }

    public void setDepartureDate( LocalDate departureDate ) {
        this.departureDate = departureDate;
    }

    public void setReturnDate( LocalDate returnDate ) {
        this.returnDate = returnDate;
    }

    public void setNumberOfPassengers( Integer numberOfPassengers ) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setMaxValue( BigDecimal maxValue ) {
        this.maxValue = maxValue;
    }

    public Specification<Ticket> createFilter() {
        return ( root, criteriaQuery, criteriaBuilder ) -> {
            List<Predicate> predicates = new ArrayList<>();


            Path<Object> flight = root
                    .get( "flight" );
            Path<Object> airRoute = flight
                    .get( "stretchs" )
                    .get( "airRoute" );
            predicates.add( criteriaBuilder.equal( airRoute.get( "origin" ).get( "id" ), airportOfOriginId ) );
            predicates.add( criteriaBuilder.equal( airRoute.get( "destiny" ).get( "id" ), airportOfDestinyId ) );
            predicates.add( criteriaBuilder.equal( root.get( "departure" ), departureDate ) );
            predicates.add( criteriaBuilder.ge( flight.get( "numberOfSeats" ), numberOfPassengers ) );

            if (roundTrip) {
                Optional.ofNullable( returnDate )
                        .ifPresent( localDate ->
                        predicates.add(
                                criteriaBuilder.equal( root.get( "departure" ) , returnDate ) )
                        );
            }

            Optional.ofNullable( maxValue )
                    .ifPresent( value ->
                    predicates.add(
                            criteriaBuilder.le( root.get( "value" ), value ) )
            );

            return criteriaBuilder.and( predicates.toArray(new Predicate[0]) );
        };
    }
}
