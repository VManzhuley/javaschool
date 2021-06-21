package com.tsystems.javaschool.util;

import com.tsystems.javaschool.dto.ClientDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        ClientDTO clientDTO=(ClientDTO) o;

        return clientDTO.getPassword().equals(clientDTO.getMatchingPassword());
    }
}
