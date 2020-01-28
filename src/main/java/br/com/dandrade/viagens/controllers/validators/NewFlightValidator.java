package br.com.dandrade.viagens.controllers.validators;

import br.com.dandrade.viagens.controllers.dto.input.NewFlightRequest;
import br.com.dandrade.viagens.controllers.dto.input.StretchDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class NewFlightValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NewFlightRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NewFlightRequest request = (NewFlightRequest) o;
        List<StretchDto> stretchs = request.getStretchs();

        long count = stretchs.stream().filter(StretchDto::isDirect).count();
        if (count > 1) {
            errors.reject("flight.stretchs.only-one-can-be-direct",
                    new Object[]{},
                    "Somente um trecho pode ser direto");
        }

        long repeated = stretchs.stream()
                .filter(s -> Collections.frequency(stretchs, s) > 1).count();
        if (repeated > 0 ) {
            errors.reject("flight.stretchs.should-not-have-repeated-stretches",
                    new Object[]{},
                    "O voo não deve ter trechos repetidos!");
        }
    }
}
