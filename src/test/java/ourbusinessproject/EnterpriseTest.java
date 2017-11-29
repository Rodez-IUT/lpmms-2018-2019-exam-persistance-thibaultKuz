package ourbusinessproject;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EnterpriseTest {

    private Validator validator;
    private Enterprise enterprise;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        // given: an enterprise with all properties correctly set
        enterprise = new Enterprise();
        enterprise.setName("Company & Co");
        enterprise.setDescription("Comp description");
        enterprise.setContactName("Paul Durand");
        enterprise.setContactEmail("paul@compco.com");
    }

    @Test
    public void testValidEnterpriseValidation() {
        // given a valid enterprise

        // the given enterprise is valid
        assertTrue("Expected no constraint violation", validator.validate(enterprise).isEmpty());

        // and the enterprise has no attached projects
        assertThat(enterprise.getProjects(), is(nullValue()));

    }

    @Test
    public void testEnterpriseNameValidation() {

        // when: the name is empty
        enterprise.setName("");

        // then: enterprise is no more valid
        assertFalse("Expected no constraint violation", validator.validate(enterprise).isEmpty());

        // when: the name is null
        enterprise.setName(null);

        // then: enterprise is no more valid
        assertFalse("Expected no constraint violation", validator.validate(enterprise).isEmpty());

    }

    @Test
    public void testEnterpriseDescriptionValidation() {

        // when the description is too short
        enterprise.setDescription("too short");

        // then: enterprise is no more valid
        assertFalse("Expected no constraint violation", validator.validate(enterprise).isEmpty());

    }

    @Test
    public void testEnterpriseContactNameValidation() {

        // when the contact name is empty
        enterprise.setContactName("");

        // then: enterprise is no more valid
        assertFalse("Expected no constraint violation", validator.validate(enterprise).isEmpty());

        // when the contact name is null
        enterprise.setContactName(null);

        // then: enterprise is no more valid
        assertFalse("Expected no constraint violation", validator.validate(enterprise).isEmpty());

    }

    @Test
    public void testEnterpriseContactEmailValidation() {

        // when the contact email is empty
        enterprise.setContactEmail("");

        // then: enterprise is no more valid
        assertFalse("Expected no constraint violation", validator.validate(enterprise).isEmpty());

        // when the contact email is null
        enterprise.setContactEmail(null);

        // then: enterprise is no more valid
        assertFalse("Expected no constraint violation", validator.validate(enterprise).isEmpty());

        // when the contact email is not an email
        enterprise.setContactEmail("not an email");

        // then: enterprise is no more valid
        assertFalse("Expected no constraint violation", validator.validate(enterprise).isEmpty());

        // tip : use @Email annotation
    }

}