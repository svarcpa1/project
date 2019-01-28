package cz.uhk.ppro.project.testing;

import cz.uhk.ppro.project.model.Role;
import cz.uhk.ppro.project.model.Worker;
import cz.uhk.ppro.project.model.Workplace;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.*;


public class WorkerValidationTest {

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
    public void shouldHaveTwoViolations() {
        Worker w1 = new Worker();

        Role r = new Role();
        r.setName("ROLE_Admin");
        Workplace wp = new Workplace("workplace11");
        wp.setId(0);

        w1.setWorkplace(wp);
        w1.setRole(r);
        w1.setFirstName("grrr");
        w1.setSurName("wrrrrr");

        Set<ConstraintViolation<Worker>> violations
                = validator.validate(w1);

        assertEquals(violations.size(), 2);
    }

    @Test
    public void shouldHaveFourViolations() {
        Worker w2 = new Worker();

        Role r = new Role();
        r.setName("ROLE_Admin");
        Workplace wp = new Workplace("workplace11");
        wp.setId(0);

        w2.setWorkplace(wp);
        w2.setRole(r);

        Set<ConstraintViolation<Worker>> violations
                = validator.validate(w2);

        assertEquals(violations.size(), 4);
    }
}
