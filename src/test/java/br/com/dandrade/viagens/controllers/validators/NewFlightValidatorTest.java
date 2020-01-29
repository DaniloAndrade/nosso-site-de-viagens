package br.com.dandrade.viagens.controllers.validators;

import br.com.dandrade.viagens.controllers.dto.input.NewFlightRequest;
import br.com.dandrade.viagens.controllers.dto.input.StretchDto;
import br.com.dandrade.viagens.models.FlightType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NewFlightValidatorTest {

    @Test
    @DisplayName("must have only a direct stretch")
    void testValidate() {
        NewFlightValidator validator = new NewFlightValidator();
        NewFlightRequest request = new NewFlightRequest();
        request.setCompanyId(1L);
        request.setNumberOfSeats(100);
        List<StretchDto> stretchs = new ArrayList<>();

        request.setStretchs(stretchs);
        Errors erros = mock(Errors.class);
        doNothing().when(erros).reject(anyString(), any(Object[].class), anyString());

        validator.validate(request, erros);
        verify(erros).reject(eq("flight.stretchs.only-one-can-be-direct"), any(Object[].class), anyString());

        clearInvocations(erros);

        StretchDto stretch1 = new StretchDto();
        stretch1.setAirRouteId(1L);
        stretch1.setType(FlightType.SCALE);
        stretchs.add(stretch1);

        validator.validate(request, erros);
        verify(erros).reject(eq("flight.stretchs.only-one-can-be-direct"), any(Object[].class), anyString());

        clearInvocations(erros);

        StretchDto stretch2 = new StretchDto();
        stretch2.setType(FlightType.DIRECT);
        stretch2.setAirRouteId(2L);
        stretchs.add(stretch2);

        validator.validate(request, erros);
        verify(erros,never()).reject(eq("flight.stretchs.only-one-can-be-direct"), any(Object[].class), anyString());

        clearInvocations(erros);

        StretchDto stretch3 = new StretchDto();
        stretch3.setType(FlightType.DIRECT);
        stretch3.setAirRouteId(3L);
        stretchs.add(stretch3);

        validator.validate(request, erros);
        verify(erros).reject(eq("flight.stretchs.only-one-can-be-direct"), any(Object[].class), anyString());
    }

    @Test
    @DisplayName("should not have repeated stretches")
    void testValidate2() {
        Errors erros = mock(Errors.class);
        doNothing().when(erros).reject(anyString(), any(Object[].class), anyString());

        NewFlightValidator validator = new NewFlightValidator();
        NewFlightRequest request = new NewFlightRequest();
        request.setCompanyId(1L);
        request.setNumberOfSeats(100);
        List<StretchDto> stretchs = new ArrayList<>();
        request.setStretchs(stretchs);


        StretchDto stretch1 = new StretchDto();
        stretch1.setAirRouteId(1L);
        stretch1.setType(FlightType.SCALE);
        stretchs.add(stretch1);

        StretchDto stretch2 = new StretchDto();
        stretch2.setType(FlightType.DIRECT);
        stretch2.setAirRouteId(1L);
        stretchs.add(stretch2);

        validator.validate(request, erros);
        verify(erros).reject(eq("flight.stretchs.should-not-have-repeated-stretches"), any(Object[].class), anyString());

        clearInvocations(erros);

        List<StretchDto> stretchs2 = new ArrayList<>();
        request.setStretchs(stretchs2);

        StretchDto stretch3 = new StretchDto();
        stretch3.setAirRouteId(1L);
        stretch3.setType(FlightType.SCALE);
        stretchs2.add(stretch3);

        StretchDto stretch4 = new StretchDto();
        stretch4.setType(FlightType.DIRECT);
        stretch4.setAirRouteId(2L);
        stretchs2.add(stretch4);

        validator.validate(request, erros);
        verify(erros, never()).reject(eq("flight.stretchs.should-not-have-repeated-stretches"), any(Object[].class), anyString());
    }
}