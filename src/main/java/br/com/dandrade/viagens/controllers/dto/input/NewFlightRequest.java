package br.com.dandrade.viagens.controllers.dto.input;

import br.com.dandrade.viagens.functions.FinderById;
import br.com.dandrade.viagens.models.AirRoute;
import br.com.dandrade.viagens.models.Company;
import br.com.dandrade.viagens.models.Flight;
import br.com.dandrade.viagens.models.Stretch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class NewFlightRequest {

    @NotNull
    @Positive
    private Integer numberOfSeats;

    @NotNull
    private Long companyId;

    @Size(min = 1)
    private Set<StretchDto> stretchs;

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setStretchs(Set<StretchDto> stretchs) {
        this.stretchs = stretchs;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Set<StretchDto> getStretchs() {
        return stretchs;
    }

    @Override
    public String toString() {
        return "NewFlightRequest{" +
                "numberOfSeats=" + numberOfSeats +
                ", stretchs=" + stretchs +
                '}';
    }

    public Flight newFlight(FinderById<Long, Optional<AirRoute>> finderAirRoute,
                            FinderById<Long, Optional<Company>> finderCompany) {
        SortedSet<Stretch> stretchList =  stretchs.stream()
                .map(s -> s.newStretch(finderAirRoute))
                .collect(Collectors.toCollection(TreeSet::new));
        Company company = finderCompany.findById(companyId).orElseThrow();
        return new Flight(numberOfSeats, company, stretchList);
    }
}
