package br.com.dandrade.viagens.controllers.validators;

import br.com.dandrade.viagens.controllers.dto.input.NewCompanyRequest;
import br.com.dandrade.viagens.repository.CompanyRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class NewCompanyValidator implements Validator {

    private CompanyRepository companyRepository;

    public NewCompanyValidator(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewCompanyRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NewCompanyRequest request = (NewCompanyRequest) o;
        boolean existsCompany = this.companyRepository.existsByName(request.getName());
        if (existsCompany){
            errors.reject("company.name.already.exists",
                    new Object[]{request.getName()},
                    "Já existe companhia com o nome informado");
        }

    }
}
