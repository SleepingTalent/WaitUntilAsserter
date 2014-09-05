package com.noveria.assertion.asserter;

import com.noveria.assertion.expection.WaitUntilAssertionError;
import com.noveria.assertion.task.AssertTask;
import com.noveria.common.BaseUnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;


public class WaitUntilAsserterTest extends BaseUnitTest {

    WaitUntilAsserter testee;

    @Mock
    AssertTask assertTask;

    @Before
    public void Setup() {

        when(assertTask.execute()).thenReturn(true);
        when(assertTask.getTaskName()).thenReturn("My Mock Task");
        when(assertTask.getFailureMessage()).thenReturn("Mocked Failure Message");

        testee = new WaitUntilAsserter(assertTask);
    }

    @Test
    public void performAssertion_passes_ifAssertTaskSuccessful() throws InterruptedException {
        testee.performAssertion();

        assertEquals(0,testee.getAccumulatedTime());

        verify(assertTask).execute();
        verify(assertTask,never()).getFailureMessage();
    }

    @Test
    public void performAssertion_passes_ifAssertTaskSuccessfulAfterTwoAttempts() throws InterruptedException {
        when(assertTask.execute()).thenReturn(false).thenReturn(true);

        testee.performAssertion();

        assertEquals(500,testee.getAccumulatedTime());

        verify(assertTask, times(2)).execute();
        verify(assertTask,never()).getFailureMessage();
    }

    @Test(expected =  WaitUntilAssertionError.class)
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessful() throws InterruptedException {
        when(assertTask.execute()).thenReturn(false);
        testee = new WaitUntilAsserter(assertTask,500);
        assertAttemptsBeforeFailure(2,500);
    }

    @Test(expected =  WaitUntilAssertionError.class)
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessfulAfterTwoAttempts() throws InterruptedException {
        when(assertTask.execute()).thenReturn(false);
        testee = new WaitUntilAsserter(assertTask,1000);
        assertAttemptsBeforeFailure(3,1000);
    }

    @Test(expected =  WaitUntilAssertionError.class)
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessfulWithDefaultTimeout() throws InterruptedException {
        when(assertTask.execute()).thenReturn(false);
        assertAttemptsBeforeFailure(11,5000);
    }

    private void assertAttemptsBeforeFailure(int expectedAttempts, int expectedTime) throws InterruptedException {

        try {
            testee.performAssertion();
        } catch (WaitUntilAssertionError assertionError) {
            assertEquals("My Mock Task assertionFailed : Mocked Failure Message",assertionError.getMessage());
            verify(assertTask,times(expectedAttempts)).execute();
            verify(assertTask).getFailureMessage();

            assertTrue("Expected Accumulated Time to be between ("+expectedTime+") and ("+expectedTime+10+")",
                    testee.getAccumulatedTime() >= expectedTime);
            throw assertionError;
        }

        fail("Expected Assertion Failure");
    }




}
