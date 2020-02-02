package br.com.dandrade.viagens.controllers.dto.input;

import br.com.dandrade.viagens.models.Ticket;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
        Specification<Ticket> oneWayTicket = oneWayTicket();

        if (!roundTrip) {
            return oneWayTicket;
        }

        return oneWayTicket.or( returnTicket() );
    }

    private Specification<Ticket> returnTicket() {
        return ( root, query, criteriaBuilder ) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Object, Object> flight = root.join( "flight" );
            Join<Object, Object> stretchs = flight.join( "stretchs" );
            Path<Object> airRoute = stretchs.get( "airRoute" );

            predicates.add( criteriaBuilder.ge( flight.get( "numberOfSeats" ), numberOfPassengers ) );
            Optional.ofNullable( maxValue )
                    .ifPresent( value ->
                                        predicates.add(
                                                criteriaBuilder.le( root.get( "value" ), value ) )
                    );
            Optional.ofNullable( returnDate )
                .ifPresent( localDate ->
                    {

                        predicates.add(
                                criteriaBuilder.greaterThanOrEqualTo( root.get( "departure" ),
                                                                      returnDate.atStartOfDay() ) );
                        predicates.add(
                                criteriaBuilder.lessThanOrEqualTo( root.get( "departure" ), returnDate
                                        .atTime( LocalTime.MAX ) ) );
                    }
                );
            predicates.add( criteriaBuilder.equal( airRoute.get( "origin" ).get( "id" ), airportOfDestinyId ) );
            predicates.add( criteriaBuilder.equal( airRoute.get( "destiny" ).get( "id" ), airportOfOriginId ) );
           return criteriaBuilder.and( predicates.toArray( new Predicate[ 0 ] ) );
        };
    }


    private Specification<Ticket> oneWayTicket() {
        return ( root, criteriaQuery, criteriaBuilder ) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Object, Object> flight = root.join( "flight" );
            Join<Object, Object> stretchs = flight.join( "stretchs" );
            Path<Object> airRoute = stretchs.get( "airRoute" );

            predicates.add( criteriaBuilder.equal( airRoute.get( "origin" ).get( "id" ), airportOfOriginId ) );
            predicates.add( criteriaBuilder.equal( airRoute.get( "destiny" ).get( "id" ), airportOfDestinyId ) );
            predicates.add( criteriaBuilder
                                    .greaterThanOrEqualTo( root.get( "departure" ), departureDate.atStartOfDay() ) );
            predicates.add( criteriaBuilder.lessThanOrEqualTo( root.get( "departure" ),
                                                               departureDate.atTime( LocalTime.MAX ) ) );
            predicates.add( criteriaBuilder.ge( flight.get( "numberOfSeats" ), numberOfPassengers ) );

            Optional.ofNullable( maxValue )
                    .ifPresent( value ->
                                        predicates.add(
                                                criteriaBuilder.le( root.get( "value" ), value ) )
                    );

            return criteriaBuilder.and( predicates.toArray( new Predicate[ 0 ] ) );
        };
    }
}
