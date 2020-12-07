package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

@SpringBootApplication
@RestController
public class GatewayApplication {
  private static HashMap<String, String> servers;

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
    initializeServers();
  }

  public static void initializeServers(){
    //TODO: move to applications/properties
    servers = new HashMap<>();
    servers.put("LOGIN", "http://localhost:8001/");
    servers.put("SQL", "http://localhost:8002/");
    servers.put("ML", "http://localhost:8003/");
  }

  @GetMapping("/{location}")
  public Object getRoute(@RequestBody(required = false) String request,
                         @RequestHeader(name = "id", defaultValue = "") String id,
                         @PathVariable String location) throws IOException, InterruptedException {
    if(!servers.containsKey(location)) return null;
    String url = servers.get(location);

    HttpClient serverClient = HttpClient.newHttpClient();
    HttpRequest serverRequest = HttpRequest.newBuilder()
            .GET()
            .header("Content-Type", "application/json")
            .header("id", id)
            .uri(URI.create(url))
            .build();
    HttpResponse<String> response = serverClient.send(serverRequest, HttpResponse.BodyHandlers.ofString());

    return response.body();
  }

  @PostMapping("/{location}")
  public Object postRoute(@RequestBody String request,
                          @PathVariable String location) throws IOException, InterruptedException {
    if(!servers.containsKey(location)) return null;
    String url = servers.get(location);
    HttpRequest.BodyPublisher body =  HttpRequest.BodyPublishers.ofString(request);

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
  public Object putRoute(@RequestBody String request,
                         @RequestHeader(name = "id", defaultValue = "") String id,
                         @PathVariable String location) throws IOException, InterruptedException {
    if(!servers.containsKey(location)) return null;
    String url = servers.get(location);
    HttpRequest.BodyPublisher body =  HttpRequest.BodyPublishers.ofString(request);

    HttpClient serverClient = HttpClient.newHttpClient();
    HttpRequest serverRequest = HttpRequest.newBuilder()
            .PUT(body)
            .header("Content-Type", "application/json")
            .header("id", id)
            .uri(URI.create(url))
            .build();
    HttpResponse<String> response = serverClient.send(serverRequest, HttpResponse.BodyHandlers.ofString());

    return response.body();
  }

  @DeleteMapping("/{location}")
  public Object deleteRoute(@RequestBody(required = false) String request,
                            @RequestHeader(name = "id", defaultValue = "") String id,
                            @PathVariable String location) throws IOException, InterruptedException {
    if(!servers.containsKey(location)) return null;
    String url = servers.get(location);

    HttpClient serverClient = HttpClient.newHttpClient();
    HttpRequest serverRequest = HttpRequest.newBuilder()
            .DELETE()
            .header("Content-Type", "application/json")
            .header("id", id)
            .uri(URI.create(url))
            .build();
    HttpResponse<String> response = serverClient.send(serverRequest, HttpResponse.BodyHandlers.ofString());

    return response.body();
  }
}


