package com.noveria.cukes.helpers.thread;

import com.noveria.cukes.helpers.rest.RestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThreadHelper {

    @Autowired
    RestHelper restHelper;

    public void startProcessCompleteUpdaterThread(int processingTime) {
        ProcessCompleteDelayThread processingCompleteDelayThread = new ProcessCompleteDelayThread(restHelper,processingTime);
        Thread thread = new Thread(processingCompleteDelayThread);
        thread.start();
    }
}
