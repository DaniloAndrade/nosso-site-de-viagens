package br.com.dandrade.viagens.controllers.validators;

import br.com.dandrade.viagens.controllers.dto.input.NewFlightRequest;
import br.com.dandrade.viagens.controllers.dto.input.StretchDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class NewFlightValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NewFlightRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NewFlightRequest request = (NewFlightRequest) o;
        List<StretchDto> stretchs = request.getStretchs();

        if (stretchs.isEmpty()) {
            errors.reject("flight.stretchs.is.required",
                    new Object[]{},
                    "Ao menos uma rota é necessaria!");
        }

        long count = stretchs.stream().filter(StretchDto::isDirect).count();
        if (count > 1) {
            errors.reject("flight.stretchs.only-one-can-be-direct",
                    new Object[]{},
                    "Somente um trecho pode ser direto");
        }
    }
}
