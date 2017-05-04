package com.noveria.cukes.helpers.rest;

import com.noveria.cukes.runtime.RuntimeState;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class RestHelper {

    public static final String REST_URL = "/processingTimeController";

    @Autowired
    RuntimeState runtimeState;

    private String getBaseUrl() {
        return runtimeState.getHost()+REST_URL;
    }

    public void setProcessingTime(int processingTime) {
        String uri = getBaseUrl()+"/processTime/{time}";
        postTime(uri, processingTime);
    }

    public Boolean processingComplete() {
        String uri = getBaseUrl()+"/processingComplete/";
        return getBooleanResponse(uri);
    }

    public void setProcessingComplete(boolean complete) {
        String uri = getBaseUrl()+"/processingComplete/{value}";
        postComplete(uri, complete);
    }

    private void postTime(String uri, int value) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = getRequest();

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("time",value);

        restTemplate.exchange(uri, HttpMethod.PUT, request, String.class,map);
    }

    private void postComplete(String uri, boolean complete) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = getRequest();

        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("value",complete);

        restTemplate.exchange(uri, HttpMethod.PUT, request, String.class,map);
    }

    private void callGet(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = getRequest();

        restTemplate.exchange(uri, HttpMethod.GET, request, String.class);;
    }

    private Boolean getBooleanResponse(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = getRequest();

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        return Boolean.valueOf(response.getBody());
    }

    private HttpEntity<String> getRequest() {
        HttpHeaders headers = new HttpHeaders();
        return new HttpEntity<String>(headers);
    }
}