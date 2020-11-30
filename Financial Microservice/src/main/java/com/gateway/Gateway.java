package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Gateway {

  public static void main(String[] args) {
    SpringApplication.run(Gateway.class, args);
  }



  @GetMapping
  public String sendRequest(@RequestParam String jsonRequest, String destination){
    return jsonRequest;
  }

}
