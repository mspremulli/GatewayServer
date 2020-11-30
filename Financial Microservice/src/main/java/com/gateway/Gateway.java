package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@SpringBootApplication
@RestController
public class Gateway {

  private static HashMap<String, Integer> servers;

  public static void initializeServers(){
    servers = new HashMap<>();
    servers.put("LOGIN", 8000);
    servers.put("SQLDB", 7000);
    servers.put("ML", 6000);
  }

  public static void main(String[] args) {
    SpringApplication.run(Gateway.class, args);
    initializeServers();
  }

  @GetMapping
  public String sendRequest(@RequestParam String jsonRequest, String destination){
    int serverAddress = servers.get(destination);

    return jsonRequest;
  }

}
