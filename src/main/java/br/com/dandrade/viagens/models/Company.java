package br.com.dandrade.viagens.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @ManyToOne
    private Country country;

    private Instant createAt;

    @Deprecated
    public Company() {
    }

    public Company(String name, Country country) {
        this.name = name;
        this.country = country;
        this.createAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", createAt=" + createAt +
                '}';
    }

    public String getCountryName() {
        return this.country.getName();
    }
}
