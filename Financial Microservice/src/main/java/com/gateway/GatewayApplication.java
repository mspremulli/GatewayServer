package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.http.HttpClient;
import java.util.HashMap;

@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
    initializeServers();
  }

  private static HashMap<String, Integer> servers;
  private final HttpClient serverConnection = HttpClient.newBuilder().build();


  public static void initializeServers(){
    servers = new HashMap<>();
    servers.put("LOGIN", 8000);
//    servers.put("SQL", 7000);
//    servers.put("ML", 6000);
  }

}


