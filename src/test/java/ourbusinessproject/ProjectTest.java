package ourbusinessproject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ProjectTest {

    private Validator validator;
    private Project project;

    @MockBean
    private Enterprise enterprise;


    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        // given : a valid project
        project = new Project("A project","Project description");
        project.setEnterprise(enterprise);

    }

    @Test
    public void testProjectValidation() {

        // given : a project with a non empty title and a non empty description

        // expect : project is valid
        assertTrue("expected no constraints violation", validator.validate(project).isEmpty());

        // when: the project has an empty description
        project.setDescription("");

        // then: the project is still valid
        assertTrue("expected no constraints violation", validator.validate(project).isEmpty());

        // when: the project has no description
        project.setDescription(null);

        // then: the project is still valid
        assertTrue("expected no constraints violation", validator.validate(project).isEmpty());

    }

    @Test
    public void testProjectWithNoTitleInvalidation() {

        // given : a project with a non empty title and a non empty description

        // when: the project has an empty title
        project.setTitle("");

        // then: the project is no more valid
        assertFalse("expected one constraint violation", validator.validate(project).isEmpty());

        // when: the project has no title
        project.setTitle(null);

        // then: the project is no more valid
        assertFalse("expected one constraint violation", validator.validate(project).isEmpty());

    }

    @Test
    public void testProjectWithNoEnterpriseInvalidation() {

        // given : a project with a non empty title and a non empty description

        // when: the project has no enterprise
        project = new Project("A project","Project description");

        // then: the project is no more valid
        assertFalse("expected one constraint violation", validator.validate(project).isEmpty());

    }

}