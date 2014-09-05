package com.noveria.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("processingTime")
public class ProcessingTimer {

    Logger log = LoggerFactory.getLogger(ProcessingTimer.class);

    long processingTime;

    public void process() {
        log.debug("Processing for = {} Milliseconds",processingTime);

        try {
            TimeUnit.MILLISECONDS.sleep(processingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setProcessingTime(long processingTime) {
        this.processingTime = processingTime;
    }
}
