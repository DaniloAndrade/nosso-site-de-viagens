package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.Stretch;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StretchDto {
    private String out;
    private String in;
    private LocalTime takeOff;
    private LocalTime landing;

    public StretchDto( String out, String in, LocalTime takeOff, LocalTime landing ) {
        this.out = out;
        this.in = in;
        this.takeOff = takeOff;
        this.landing = landing;
    }


    public String getOut() {
        return out;
    }

    public String getIn() {
        return in;
    }

    public LocalTime getTakeOff() {
        return takeOff;
    }

    public LocalTime getLanding() {
        return landing;
    }

    public static List<StretchDto> create( Collection<Stretch> stretchs, LocalDateTime departure ) {

        List<StretchDto> stretchDtos = new ArrayList<>(  );
        LocalTime stretchTime = departure.toLocalTime();

        for (Stretch stretch: stretchs) {
            LocalTime takeOff = stretchTime;
            LocalTime landing = takeOff.plus( stretch.getAirRoute().getDuration(), ChronoUnit.MINUTES );

            StretchDto stretchDto = new StretchDto( stretch.getAirRoute().getOriginAirportName(),
                                                    stretch.getAirRoute().getDestinyAirportName(), takeOff, landing );
            stretchTime = landing.plus( stretch.getStopTime(), ChronoUnit.MINUTES );
            stretchDtos.add( stretchDto );
        }

        return stretchDtos;
    }


}
