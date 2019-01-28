package cz.uhk.ppro.project.validation;

import cz.uhk.ppro.project.model.Role;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleValidator
        implements ConstraintValidator<RoleConstraint, Role> {
    public void initialize(RoleConstraint constraintAnnotation) {
    }

    public boolean isValid(Role value, ConstraintValidatorContext context) {
        if (value.getId()!=0) return true;
        else return false;
    }
}