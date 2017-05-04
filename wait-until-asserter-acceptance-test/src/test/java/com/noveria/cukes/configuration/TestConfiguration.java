package com.noveria.cukes.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.noveria","com.noveria.cukes"})
public class TestConfiguration {}