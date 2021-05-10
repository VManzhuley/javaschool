package com.tsystems.javaschool.validatition;

import com.tsystems.javaschool.dto.ClientDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AddressValidation implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ClientDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"country","NotEmpty.clientForm.country");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"city","NotEmpty.clientForm.city");

    }
}
