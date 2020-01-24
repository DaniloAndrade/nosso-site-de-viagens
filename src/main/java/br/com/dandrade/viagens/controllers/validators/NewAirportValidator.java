package br.com.dandrade.viagens.controllers.validators;

import br.com.dandrade.viagens.controllers.dto.input.NewAirportRequest;
import br.com.dandrade.viagens.repository.AirportRespository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class NewAirportValidator implements Validator {
    private AirportRespository respository;

    public NewAirportValidator(AirportRespository respository) {
        this.respository = respository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewAirportRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NewAirportRequest request = (NewAirportRequest) o;
        boolean existsAirport = respository.existsByName(request.getName());
        if (existsAirport){
            errors.reject("airport.name.already.exists",
                    new Object[]{request.getName()},
                    "Já existe aeroporto com o nome informado");
        }
    }
}
