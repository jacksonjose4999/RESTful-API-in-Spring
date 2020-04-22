package com.example.demo.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NameValidatorCheck implements  ConstraintValidator<NameValidator, String> {

    @Override
    public void initialize(NameValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ((s != null) && (!s.equals("")) && isAlpha(s));
    }

    private static boolean isAlpha(String s) {
        return s != null && s.chars().allMatch(Character::isLetter);
    }
}
