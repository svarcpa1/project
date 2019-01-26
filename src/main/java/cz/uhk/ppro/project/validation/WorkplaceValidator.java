package cz.uhk.ppro.project.validation;

import cz.uhk.ppro.project.model.Workplace;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WorkplaceValidator
        implements ConstraintValidator<WorkplaceConstraint, Workplace> {
    public void initialize(WorkplaceConstraint constraintAnnotation) {
    }

    public boolean isValid(Workplace value, ConstraintValidatorContext context) {
        if (value.getId()!=0) return true;
        else return false;
    }
}