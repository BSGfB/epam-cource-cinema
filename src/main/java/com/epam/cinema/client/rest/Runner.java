package com.epam.cinema.client.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Runner {
    public static final String HTTP_LOCALHOST_8080_REST = "http://localhost:8080/rest";
    public static final String USERS = "/users";
    public static final int USER_ID = 1;

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        String httpRequest = new StringBuffer()
                .append(HTTP_LOCALHOST_8080_REST)
                .append(USERS).append("/getById?id={id}").toString();

        System.out.println("Request: " + httpRequest);

        ResponseEntity responseEntity = restTemplate.getForEntity(httpRequest, String.class, USER_ID);
        System.out.println("Result: " + responseEntity.getBody());


        String postRequest = new StringBuffer()
                .append(HTTP_LOCALHOST_8080_REST)
                .append(USERS).append("/add").toString();

        System.out.println("Post Request: " + postRequest);
        System.out.println("Body: " + userJson());



        HttpEntity<String> entity = new HttpEntity<String>(userJson(), creaHttpHeaders());

        responseEntity = restTemplate.postForEntity(postRequest, entity, String.class);
        System.out.println("Result user id: " + responseEntity.getBody());

    }

    static String userJson() {
        return "{\"firstName\":\"bob\", \"lastName\":\"Kek\",\"email\":\"bob@mail\", \"birthday\":\"2000-10-10\", \"password\": \"123\"}";
    }

    static HttpHeaders creaHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
