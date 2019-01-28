package cz.uhk.ppro.project.testing;

import cz.uhk.ppro.project.model.Hall;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.*;

public class HallValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }


    @Test
    public void shouldHaveNoViolations() {
        Hall h1 = new Hall();
        h1.setName("grrr");

        Set<ConstraintViolation<Hall>> violations
                = validator.validate(h1);

        assertEquals(violations.size(), 0);
    }

    @Test
    public void shouldHaveViolation() {
        Hall h1 = new Hall();

        Set<ConstraintViolation<Hall>> violations
                = validator.validate(h1);

        assertEquals(violations.size(), 1);
    }
}
