package com.noveria.assertion.asserter;

import com.noveria.assertion.task.AssertTask;
import com.noveria.common.BaseUnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class WaitUntilAsserterTest extends BaseUnitTest {

    WaitUntilAsserter testee;

    @Mock
    AssertTask assertTask;

    @Before
    public void Setup() {
        testee = new WaitUntilAsserter(assertTask,500);
        when(assertTask.execute()).thenReturn(false);
        when(assertTask.getTaskName()).thenReturn("My Mock Task");
        when(assertTask.getFailureMessage()).thenReturn("Mocked Failure Message");
    }

    @Test
    public void performAssertion_passes_ifAssertTaskSuccessful() throws InterruptedException {
        testee = new WaitUntilAsserter(assertTask);
        when(assertTask.execute()).thenReturn(true);
        testee.performAssertion();
    }

    @Test(expected = java.lang.AssertionError.class)
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessful() throws InterruptedException {
        testee.performAssertion();
        verify(assertTask, times(1)).getFailureMessage();
    }

    @Test(expected = java.lang.AssertionError.class)
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessfulAfterTwoAttempts() throws InterruptedException {
        testee.performAssertion();
        verify(assertTask, times(1)).execute();
        verify(assertTask, times(1)).getFailureMessage();
    }
}
