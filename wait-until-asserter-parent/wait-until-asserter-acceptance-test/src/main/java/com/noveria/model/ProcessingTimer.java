package com.noveria.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("processingTime")
public class ProcessingTimer {

    Logger log = LoggerFactory.getLogger(ProcessingTimer.class);

    long processingTime;
    private boolean complete = false;

    public void setProcessingTime(long processingTime) {
        log.debug("Setting Processing Time to {} Milliseconds",processingTime);
        this.processingTime = processingTime;
    }

    public boolean processComplete() {
        log.debug("Return Complete Status {}",complete);
        return complete;
    }

    public void setComplete(boolean complete) {
        log.debug("Setting Complete Status to {}",complete);
        this.complete = complete;
    }
}
