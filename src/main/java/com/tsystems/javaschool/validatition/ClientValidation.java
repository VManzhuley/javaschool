package com.tsystems.javaschool.validatition;

import com.tsystems.javaschool.dto.ClientDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class ClientValidation implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return ClientDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ClientDTO client = (ClientDTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","NotEmpty.clientForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastname","NotEmpty.clientForm.lastname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","NotEmpty.clientForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"phone","NotEmpty.clientForm.phone");
    }
}
