package com.noveria.common;

import org.mockito.MockitoAnnotations;

public abstract class BaseUnitTest {

    protected BaseUnitTest() {
        MockitoAnnotations.initMocks(this);
    }
}
