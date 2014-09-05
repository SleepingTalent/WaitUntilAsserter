package com.noveria.assertion.factory;

import com.noveria.assertion.asserter.WaitUntilAsserter;
import com.noveria.assertion.task.AssertTask;
import com.noveria.common.BaseUnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AssertionFactoryTest extends BaseUnitTest {

    AssertionFactory testee;

    @Mock
    AssertTask assertTask;

    @Before
    public void Setup() {

        when(assertTask.execute()).thenReturn(true);
        when(assertTask.getTaskName()).thenReturn("My Mock Task");
        when(assertTask.getFailureMessage()).thenReturn("Mocked Failure Message");

        testee = new AssertionFactory();
    }

    @Test
    public void performAssertion_passes_ifAssertTaskSuccessful() throws InterruptedException {
        testee.performAssertion(assertTask);

        verify(assertTask).execute();
        verify(assertTask,never()).getFailureMessage();
    }

    @Test
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessful() throws InterruptedException {
        when(assertTask.execute()).thenReturn(false);
        testee = new AssertionFactory();

        try {
            testee.performAssertion(assertTask,500);
        } catch (AssertionError assertionError) {
            assertEquals("My Mock Task assertionFailed : Mocked Failure Message",assertionError.getMessage());
            verify(assertTask,times(2)).execute();
            verify(assertTask,times(2)).getFailureMessage();
        }
    }

    @Test
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessfulAfterTwoAttempts() throws InterruptedException {
        when(assertTask.execute()).thenReturn(false);
        testee = new AssertionFactory();

        try {
            testee.performAssertion(assertTask,1000);
        } catch (AssertionError assertionError) {
            assertEquals("My Mock Task assertionFailed : Mocked Failure Message",assertionError.getMessage());
            verify(assertTask,times(3)).execute();
            verify(assertTask,times(2)).getFailureMessage();
        }
    }

    @Test
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessfulWithDefaultTimeout() throws InterruptedException {
        when(assertTask.execute()).thenReturn(false);

        try {
            testee.performAssertion(assertTask);
        } catch (AssertionError assertionError) {
            assertEquals("My Mock Task assertionFailed : Mocked Failure Message",assertionError.getMessage());
            verify(assertTask,times(11)).execute();
            verify(assertTask,times(2)).getFailureMessage();
        }
    }

}
