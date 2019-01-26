package cz.uhk.ppro.project.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = WorkplaceValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkplaceConstraint {

    String message() default "Pracoviště je povinné";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}