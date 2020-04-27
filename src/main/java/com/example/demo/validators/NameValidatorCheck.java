package com.example.demo.validators;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


public class NameValidatorCheck implements  ConstraintValidator<NameValidator, String> {

    @Override
    public void initialize(NameValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();

        if (s.equals("") || !isAlpha(s)) {
            String errorMessage = "";

            if (s.equals("")) {
                errorMessage = "Name is empty";
                constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
            }
            if (stringContainsNumber(s)){
                errorMessage = "Name contains numbers";
                constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
            }
            if (stringContainsSpecialChars(s)) {
                errorMessage = "Name has special characters";
                constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
            }
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
