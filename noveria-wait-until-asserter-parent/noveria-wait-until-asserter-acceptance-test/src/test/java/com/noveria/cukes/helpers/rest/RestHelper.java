package com.noveria.cukes.helpers.rest;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component("restHelper")
public class RestHelper {

    public static final String BASE_URL = "http://localhost:4094/testing-service/processingTimeController/";

    public void callProcessOnServer() {
        String uri = BASE_URL+"process/";
        callGet(uri);
    }

    public void setProcessingTime(int processingTime) {
        String uri = BASE_URL+"/processTime/{time}";
        postTime(uri, processingTime);
    }

    public Boolean processingComplete() {
        String uri = BASE_URL+"/processingComplete/";
        return getBooleanResponse(uri);
    }

    public void setProcessingComplete(boolean complete) {
        String uri = BASE_URL+"processingComplete/{value}";
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

    //@Test
    public void defaultTest() {
        setProcessingTime(1500);
        callProcessOnServer();
    }
}