package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
		
		user.setName(userDetails.getName());
		user.setEmail(userDetails.getEmail());
		
		return userRepository.save(user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
		
		userRepository.delete(user);
	}
}
