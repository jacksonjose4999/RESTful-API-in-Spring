package com.example.demo.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NameValidatorCheck implements  ConstraintValidator<NameValidator, String> {

    @Override
    public void initialize(NameValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.equals("") || !isAlpha(s) || s ==null) {
            String errorMessage = "";

            if (s.equals("")) {
                errorMessage = errorMessage + "Name is empty \n";
            }
            if (stringContainsNumber(s)){
                errorMessage = errorMessage + "Name contains numbers \n";
            }
            if (stringContainsSpecialChars(s)) {
                errorMessage = errorMessage + "Name has special characters";
            }
            constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean stringContainsSpecialChars(String s) {
        return Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE).matcher(s).find();
    }

    private static boolean isAlpha(String s) {
        return s.chars().allMatch(Character::isLetter);
    }

    private boolean stringContainsNumber(String s) {
         return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }


}
