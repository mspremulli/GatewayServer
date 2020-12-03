package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.HashMap;
//import org.json.*;

@SpringBootApplication
@RestController
public class GatewayApplication {
  private static HashMap<String, String> servers;

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
    initializeServers();
  }

  public static void initializeServers(){
    servers = new HashMap<>();
    servers.put("LOGIN", "http://localhost:8000/");
    servers.put("SQL", "http://localhost:7000/");
    servers.put("ML", "http://localhost:6000/");
    servers.put("TEST", "https://jsonmock.hackerrank.com/api/movies/search/?Title=");
  }

  @GetMapping("/{location}")
  public Object getRoute(@RequestBody String request, @PathVariable String location) throws IOException, InterruptedException {
    String url = servers.get(location) + request;

    HttpClient serverClient = HttpClient.newHttpClient();
    HttpRequest serverRequest = HttpRequest.newBuilder()
            .GET()
            .header("Content-Type", "application/json")
            .uri(URI.create(url))
            .build();
    HttpResponse<String> response = serverClient.send(serverRequest, HttpResponse.BodyHandlers.ofString());

    return response.body();
  }

  @PostMapping("/{location}")
  public Object postRoute(@RequestBody String request, @PathVariable String location) throws IOException, InterruptedException {
    String url = servers.get(location) + request;
    HttpRequest.BodyPublisher body =  HttpRequest.BodyPublishers.ofFile(Paths.get(request));


    HttpClient serverClient = HttpClient.newHttpClient();
    HttpRequest serverRequest = HttpRequest.newBuilder()
            .POST(body)
            .header("Content-Type", "application/json")
            .uri(URI.create(url))
            .build();
    HttpResponse<String> response = serverClient.send(serverRequest, HttpResponse.BodyHandlers.ofString());

    return response.body();
  }

  @PutMapping("/{location}")
  public Object putRoute(@RequestBody String request, @PathVariable String location) throws IOException, InterruptedException {
    String url = servers.get(location) + request;
    HttpRequest.BodyPublisher body =  HttpRequest.BodyPublishers.ofFile(Paths.get(request));

    HttpClient serverClient = HttpClient.newHttpClient();
    HttpRequest serverRequest = HttpRequest.newBuilder()
            .PUT(body)
            .header("Content-Type", "application/json")
            .uri(URI.create(url))
            .build();
    HttpResponse<String> response = serverClient.send(serverRequest, HttpResponse.BodyHandlers.ofString());

    return response.body();
  }

  @DeleteMapping("/{location}")
  public Object deleteRoute(@RequestBody String request, @PathVariable String location) throws IOException, InterruptedException {
    String url = servers.get(location) + request;

    HttpClient serverClient = HttpClient.newHttpClient();
    HttpRequest serverRequest = HttpRequest.newBuilder()
            .DELETE()
            .header("Content-Type", "application/json")
            .uri(URI.create(url))
            .build();
    HttpResponse<String> response = serverClient.send(serverRequest, HttpResponse.BodyHandlers.ofString());

    return response.body();
  }
}


