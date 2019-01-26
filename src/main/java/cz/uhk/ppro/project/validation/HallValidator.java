package cz.uhk.ppro.project.validation;

import cz.uhk.ppro.project.model.Hall;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HallValidator
        implements ConstraintValidator<HallConstraint, Hall> {
    public void initialize(HallConstraint constraintAnnotation) {
    }

    public boolean isValid(Hall value, ConstraintValidatorContext context) {
        if (value.getId()!=0) return true;
        else return false;
    }
}