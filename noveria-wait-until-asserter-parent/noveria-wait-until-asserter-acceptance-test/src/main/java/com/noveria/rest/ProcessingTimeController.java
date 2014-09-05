package com.noveria.rest;

import com.noveria.model.ProcessingTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/processingTimeController")
public class ProcessingTimeController {

    @Autowired
    ProcessingTimer processingTimer;

    @RequestMapping(value = "/process/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void process() {
       processingTimer.process();
    }

    @RequestMapping(value = "/processTime/{time}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void setProcessTime(@PathVariable("time")int time) {
        processingTimer.setProcessingTime(time);
    }
}
