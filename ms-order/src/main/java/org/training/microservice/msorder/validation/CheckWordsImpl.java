package org.training.microservice.msorder.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class CheckWordsImpl implements ConstraintValidator<CheckWords, String> {
    private CheckWords anno;

    @Override
    public void initialize(final CheckWords annoParam) {
        anno = annoParam;
    }

    @Override
    public boolean isValid(final String value,
                           final ConstraintValidatorContext context) {
        return Arrays.stream(anno.value())
                     .noneMatch(value::contains);
    }
}
