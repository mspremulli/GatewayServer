package com.gateway.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@RestController
public class GatewayApplication {

	public static void main(String[] args) {

		SpringApplication.run(GatewayApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@Autowired
	public GatewayApplication() {

	}

	@GetMapping(path = "{id}")
	public User getUserById(@PathVariable("id") int id){
		return null;
	}

	@PostMapping
	public User createUser(){
		return new User();
	}

	@DeleteMapping
	public User deleteUserById(int id){
		return null;
	}

	@PutMapping
	public User updateUserById(int id, User newUser){
		return newUser;
	}

}
