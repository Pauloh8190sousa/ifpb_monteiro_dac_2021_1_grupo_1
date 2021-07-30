package com.library.controllers;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;


   @Test
    public void requestBookSuccess() throws Exception{
       ResponseEntity<String> result = template.getForEntity("/createBook", String.class);
       assertEquals(HttpStatus.OK, result.getStatusCode());

   }

    @Test
    public void requestBookError() throws Exception{
        ResponseEntity<String> result = template.getForEntity("/createBook/1", String.class);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

    }

    @Test
    public void listBookSuccess() throws Exception{
        ResponseEntity<String> result = template.getForEntity("/listBook", String.class);
        assertNotEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

    }

}
