package br.com.dandrade.viagens.controllers.dto.output;

import br.com.dandrade.viagens.models.AirRoute;
import br.com.dandrade.viagens.models.Stretch;
import br.com.dandrade.viagens.models.StretchMinimalInfo;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class StretchDto {
    private String out;
    private String in;
    private LocalTime takeOff;
    private LocalTime landing;

    public StretchDto( AirRoute airRoute,  LocalTime takeOff, LocalTime landing ) {
        this(airRoute.getOriginAirportName(), airRoute.getDestinyAirportName(), takeOff, landing);
    }

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

    public static List<StretchDto> create( StretchMinimalInfo minimalInfo ) {

        List<StretchDto> stretchDtos = new ArrayList<>(  );
        LocalTime takeOff = minimalInfo.getDeparture().toLocalTime();

        for (Stretch stretch: minimalInfo.getStretchs()) {
            LocalTime landing = stretch.landingTime( takeOff, ChronoUnit.MINUTES );
            StretchDto stretchDto = new StretchDto( stretch.getAirRoute(), takeOff, landing );
            takeOff = stretch.takeOffTime(landing, ChronoUnit.MINUTES);
            stretchDtos.add( stretchDto );
        }

        return stretchDtos;
    }


}
