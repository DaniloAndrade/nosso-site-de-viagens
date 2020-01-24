package br.com.dandrade.viagens.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @ManyToOne
    private Country country;


    @Deprecated
    public Airport() {
    }

    public Airport(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryName() {
        return this.country.getName();
    }
}
