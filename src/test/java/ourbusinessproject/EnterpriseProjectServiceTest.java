package ourbusinessproject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
public class EnterpriseProjectServiceTest {

    private EnterpriseProjectService enterpriseProjectService;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private Project project;

    @MockBean
    private Enterprise enterprise;

    private Long anId = 1L;

    @Before
    public void setUp() throws Exception {
        enterpriseProjectService = new EnterpriseProjectService();
        enterpriseProjectService.setEntityManager(entityManager);
        given(this.project.getEnterprise()).willReturn(enterprise);
    }


    @Test
    public void testEntityManagerFindAProjectWhenProjectIsSearchedById() {

        // when: trying to saveEnterprise the project
        enterpriseProjectService.findProjectById(anId);

        // then: the find method is invoke on the entity manager
        verify(entityManager).find(Project.class, anId);
    }

    @Test
    public void testEntityManagerFindAnEnterpriseWhenEnterpriseIsSearchedById() {

        // when: trying to saveEnterprise the project
        enterpriseProjectService.findEnterpriseById(anId);

        // then: the find method is invoke on the entity manager
        verify(entityManager).find(Enterprise.class, anId);
    }



}