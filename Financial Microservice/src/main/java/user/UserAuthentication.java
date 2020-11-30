package user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@SpringBootApplication
@RestController
public class UserAuthentication {
	//todo replace user list with users from database
	private final ArrayList<User> users = new ArrayList<>();


	@Autowired
	public UserAuthentication() {

	}

	public static void main(String[] args) {
		SpringApplication.run(UserAuthentication.class, args);
	}

	@PostMapping
	public User createUser(@RequestBody String name, String password, int id){
		User user = new User(name, password, id);
		System.out.println("new user: " + name + " " + password + " "+ id);

		return user;
	}

	@GetMapping(path = "{id}")
	public User getUserById(@PathVariable("id") int id){
		System.out.println("in get" + id);
		User thisUser = users.stream().filter(
						user -> user.getId() == id
		).findFirst().orElse(null);

		System.out.println(thisUser);

		return thisUser;
	}

	@DeleteMapping
	public User deleteUserById(int id){
		//todo find user by id and delete
		return null;
	}

	@PutMapping
	public User updateUserById(int id, User newUser){
		//todo remove old user
		return newUser;
	}

}
