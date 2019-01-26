package cz.uhk.ppro.project.validation;

import cz.uhk.ppro.project.model.Worker;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WorkerValidator
        implements ConstraintValidator<WorkerConstraint, Worker> {
    public void initialize(WorkerConstraint constraintAnnotation) {
    }

    public boolean isValid(Worker value, ConstraintValidatorContext context) {
        if (value.getId()!=0) return true;
        else return false;
    }
}