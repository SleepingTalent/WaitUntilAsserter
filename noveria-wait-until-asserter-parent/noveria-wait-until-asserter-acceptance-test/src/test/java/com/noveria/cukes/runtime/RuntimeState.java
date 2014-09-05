package com.noveria.cukes.runtime;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class RuntimeState {

    public void setInitialState() {
        System.err.println("Setting an initial state");
    }

    public void somethingHappens() {
        System.err.println("Something has happened!");
    }

    public void expectResult() {
        System.err.println("Expected Result");
    }
}
