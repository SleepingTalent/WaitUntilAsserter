package com.noveria.assertion.asserter;

import com.noveria.assertion.exception.WaitUntilAssertionError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;


public class WaitUntilAsserterTest {

    @Mock
    WaitUntilAsserter testee;

    @Before
    public void Setup() throws InterruptedException {
        MockitoAnnotations.initMocks(this);

        when(testee.execute()).thenReturn(true);
        when(testee.getTaskName()).thenReturn("My Mock Task");
        when(testee.getFailureMessage()).thenReturn("Mocked Failure Message");

        doCallRealMethod().when(testee).performAssertion();
        doCallRealMethod().when(testee).getAccumulatedTime();
    }

    @Test
    public void defaultMaxWaitTime_setAs_expected() {
       TestWaitAsserter testWaitAsserter = new TestWaitAsserter();
       assertEquals(5000,testWaitAsserter.getMaxWaitTime());
    }

    @Test
    public void maxWaitTime_setAs_expected() {
        TestWaitAsserter testWaitAsserter = new TestWaitAsserter();
        testWaitAsserter.setMaxWaitTime(6000);

        assertEquals(6000,testWaitAsserter.getMaxWaitTime());
    }

    @Test
    public void failureMessage_setAs_expected() {
        TestWaitAsserter testWaitAsserter = new TestWaitAsserter();
        assertEquals("TestWaitAsserter Faillure Message",testWaitAsserter.getFailureMessage());
    }

    @Test
    public void taskName_setAs_expected() {
        TestWaitAsserter testWaitAsserter = new TestWaitAsserter();
        assertEquals("TestWaitAsserter",testWaitAsserter.getTaskName());
    }

    @Test
    public void performAssertion_passes_ifAssertTaskSuccessful() throws InterruptedException {
        testee.performAssertion();

        assertEquals(0,testee.getAccumulatedTime());

        verify(testee).execute();
        verify(testee,never()).getFailureMessage();
    }

    @Test
    public void performAssertion_passes_ifAssertTaskSuccessfulAfterTwoAttempts() throws InterruptedException {
        when(testee.execute()).thenReturn(false).thenReturn(true);
        when(testee.getMaxWaitTime()).thenReturn(500l);


        testee.performAssertion();

        verify(testee, times(2)).execute();
        verify(testee,never()).getFailureMessage();
    }

    @Test(expected =  WaitUntilAssertionError.class)
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessful() throws InterruptedException {
        when(testee.execute()).thenReturn(false);
        assertAttemptsBeforeFailure(2,500);
    }

    @Test(expected =  WaitUntilAssertionError.class)
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessfulAfterTwoAttempts() throws InterruptedException {
        when(testee.execute()).thenReturn(false);
        assertAttemptsBeforeFailure(3,1000);
    }

    @Test(expected =  WaitUntilAssertionError.class)
    public void performAssertion_throwsAssetionError_ifAssertTaskUnSuccessfulWithDefaultTimeout() throws InterruptedException {
        when(testee.execute()).thenReturn(false);
        assertAttemptsBeforeFailure(11,5000);
    }

    private void assertAttemptsBeforeFailure(int expectedAttempts, long expectedTime) throws InterruptedException {
        when(testee.getMaxWaitTime()).thenReturn(expectedTime);

        try {
            testee.performAssertion();
        } catch (WaitUntilAssertionError assertionError) {
            assertEquals("My Mock Task assertionFailed : Mocked Failure Message",assertionError.getMessage());
            verify(testee,times(expectedAttempts)).execute();
            verify(testee).getFailureMessage();

            assertTrue("Expected Accumulated Time to be between ("+expectedTime+") and ("+expectedTime+10+")",
                    testee.getAccumulatedTime() >= expectedTime);
            throw assertionError;
        }

        fail("Expected Assertion Failure");
    }




}
