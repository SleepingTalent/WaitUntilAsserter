package com.noveria.rest;

import com.noveria.model.ProcessingTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/processingTimeController")
public class ProcessingTimeController {

    @Autowired
    ProcessingTimer processingTimer;

    @RequestMapping(value = "/processTime/{time}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void setProcessTime(@PathVariable("time")int time) {
        processingTimer.setProcessingTime(time);
    }


    @RequestMapping(value = "/processingComplete/", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> getProcessComplete() {
        String count = String.valueOf(processingTimer.processComplete());
        return new ResponseEntity<String>(count, HttpStatus.OK);
    }

    @RequestMapping(value = "/processingComplete/{value}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void setProcessComplete(@PathVariable("value") boolean value) {
        processingTimer.setComplete(value);
    }


}
