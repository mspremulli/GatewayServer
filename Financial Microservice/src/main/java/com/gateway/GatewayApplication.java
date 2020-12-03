package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
//import org.json.*;

@SpringBootApplication
public class GatewayApplication {
  private static HashMap<String, String> servers;
  private HttpClient serverClient = HttpClient.newBuilder().build();
  private HttpRequest serverRequest;

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
    initializeServers();
  }

  public static void initializeServers(){
    servers = new HashMap<>();
    servers.put("LOGIN", "http://localhost:8000/");
    servers.put("SQL", "http://localhost:7000/");
    servers.put("ML", "http://localhost:6000/");
    servers.put("TEST", "https://jsonmock.hackerrank.com/api/movies/search/?Title=spiderman");
  }

  public String switchRoute(@RequestBody String request, @RequestParam String location){
    String url = servers.get(location);
    serverRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
    serverClient.sendAsync(serverRequest, HttpResponse.BodyHandlers.ofString());

    return serverRequest.toString();
  }

}


