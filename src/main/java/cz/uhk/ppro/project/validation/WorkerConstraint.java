package cz.uhk.ppro.project.validation;

import javax.validation.Constraint;
        import javax.validation.Payload;
        import java.lang.annotation.ElementType;
        import java.lang.annotation.Retention;
        import java.lang.annotation.RetentionPolicy;
        import java.lang.annotation.Target;

@Constraint(validatedBy = WorkerValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkerConstraint {

    String message() default "Autor je povinný";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}