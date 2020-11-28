package com.gateway.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@RestController
public class GatewayApplication {
	private final ArrayList<User> users = new ArrayList<>();


	@Autowired
	public GatewayApplication() {

	}

	@GetMapping(path = "{id}")
	public User getUserById(@PathVariable("id") UUID id){
		return users.stream().filter(
						user -> user.getUserID().equals(id)
		).findFirst().orElse(null);
	}

	@PostMapping
	public User createUser(String name, String password, UUID id){
		return new User(name, password, id);
	}

	@DeleteMapping
	public User deleteUserById(UUID id){
		//todo find user by id and delete
		return null;
	}

	@PutMapping
	public User updateUserById(UUID id, User newUser){
		//todo remove old user
		return newUser;
	}

}
