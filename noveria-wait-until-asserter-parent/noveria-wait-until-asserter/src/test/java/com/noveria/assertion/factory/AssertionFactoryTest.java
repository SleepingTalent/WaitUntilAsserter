package com.noveria.assertion.factory;

import com.noveria.assertion.task.AssertTask;
import com.noveria.common.BaseUnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AssertionFactoryTest extends BaseUnitTest {

    AssertionFactory testee;

    @Mock
    AssertTask assertTask;

    @Before
    public void Setup() {
        initMocks(this);
        testee = new AssertionFactory();
        when(assertTask.execute()).thenReturn(false);
    }

    @Test
    public void performAssertion_passes_ifAssertTaskSuccessful() throws InterruptedException {
        when(assertTask.execute()).thenReturn(true);
        testee.performAssertion(assertTask);
    }

    @Test(expected = java.lang.AssertionError.class)
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessful() throws InterruptedException {
        testee.performAssertion(assertTask,500);
    }

    @Test(expected = java.lang.AssertionError.class)
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessfulAfterTwoAttempts() throws InterruptedException {
        testee.performAssertion(assertTask,1500);
    }
}
