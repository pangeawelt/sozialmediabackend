package com.projekt.sozial.controllers;

import java.util.List;
import java.util.Optional;


import com.projekt.sozial.entity.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.projekt.sozial.repositories.UserRepository;

@RestController
@RequestMapping("/users") // root mapping
public class UserController {

	private UserRepository userRepository;

	//Constructor Injektion oder @Autowired auch möglich
	private UserController(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	// ruft alles User
	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// user create
	@PostMapping
	public User createUser(@RequestBody User newUser) {
		return userRepository.save(newUser);// JPA SAVE

	}

	// root Path ist /users dann fügt users/{userId}
	@GetMapping("/{userId}")
	// custom exception
	public User getOneUser(@PathVariable Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@PutMapping("/{userid}") // stattdesen PostMapping
	public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUserName(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			userRepository.save(foundUser);
			return foundUser;
		} else {
			return null;
		}

	}

	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable Long userId) {
		userRepository.deleteById(userId);

	}
}
