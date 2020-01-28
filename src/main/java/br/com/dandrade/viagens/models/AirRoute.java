package br.com.dandrade.viagens.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Entity
public class AirRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Airport origin;

    @OneToOne
    private Airport destiny;

    private Long duration;

    @Deprecated
    public AirRoute() {
    }

    AirRoute(String name, Airport origin, Airport destiny, Long duration) {
        this.name = name;
        this.origin = origin;
        this.destiny = destiny;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestiny() {
        return destiny;
    }

    public Long getDuration() {
        return duration;
    }

    public String getOriginAirportName() {
        return this.origin.getName();
    }

    public String getDestinyAirportName() {
        return this.destiny.getName();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirRoute airRoute = (AirRoute) o;
        return origin.equals(airRoute.origin) &&
                destiny.equals(airRoute.destiny);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destiny);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private Airport origin;
        private Airport destiny;
        private Long duration;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withOrigin(Airport origin) {
            this.origin = origin;
            return this;
        }

        public Builder withDestiny(Airport destiny) {
            this.destiny = destiny;
            return this;
        }

        public Builder withDuration(Long duration) {
            this.duration = duration;
            return this;
        }

        public AirRoute build() {
            String name = Optional.ofNullable(this.name)
                    .filter(n -> !n.isEmpty())
                    .orElseGet(() -> origin.getName() + "-" + destiny.getName());
            return new AirRoute(name, origin, destiny, duration);
        }
    }

}
