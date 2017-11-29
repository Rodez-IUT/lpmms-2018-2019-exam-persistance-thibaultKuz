package ourbusinessproject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class BootstrapTest {

    private Bootstrap bootstrap;

    @MockBean
    private InitializationService initializationService;


    @Before
    public void setUp() {
        bootstrap = new Bootstrap(initializationService);
    }

    @Test
    public void testInitMethodInvokeInitializationService() {

        // given a bootstrap and its initialization service
        assertThat(bootstrap, is(notNullValue()));

        // when the init method is triggered
        bootstrap.init();

        // then the initialization of projects is triggered on the initialization service
        verify(initializationService).initProjects();
    }

    @Test
    public void testIniBootstrapMethodCatchRuntimeExceptionComingFromInitProjects() {
        // given a bootstrap and its initialization service throwing an exception
        willThrow(RuntimeException.class).given(initializationService).initProjects();

        // when the init method is triggered
        bootstrap.init();

        // then no exception is thrown

    }

}