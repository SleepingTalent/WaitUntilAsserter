package com.noveria.cukes.helpers.thread;

import com.noveria.cukes.helpers.rest.RestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ProcessCompleteDelayThread implements Runnable {

    Logger log = LoggerFactory.getLogger(ProcessCompleteDelayThread.class);

    int processingTime;
    RestHelper restHelper;

    public ProcessCompleteDelayThread(RestHelper restHelper, int processingTime) {
        this.processingTime = processingTime;
        this.restHelper = restHelper;
    }

    @Override
    public void run()
    {
        log.debug("Sleeping for = {} Milliseconds",processingTime);

        try {
            TimeUnit.MILLISECONDS.sleep(processingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       restHelper.setProcessingComplete(true);
    }

}
