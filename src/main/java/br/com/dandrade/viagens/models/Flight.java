package br.com.dandrade.viagens.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Integer numberOfSeats;

    @Size(min = 1)
    @ElementCollection
    private List<Stretch> stretchs = new LinkedList<>();


    @Deprecated
    public Flight() {
    }

    public Flight(Integer numberOfSeats, List<Stretch> stretchs) {
        this.numberOfSeats = numberOfSeats;
        this.stretchs = stretchs;
    }


    public Long getId() {
        return id;
    }


    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public List<Stretch> getStretchs() {
        return stretchs;
    }
}
