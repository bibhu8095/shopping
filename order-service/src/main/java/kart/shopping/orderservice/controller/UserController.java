package kart.shopping.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kart.shopping.orderservice.implservice.UserServiceImpl;
import kart.shopping.orderservice.model.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
	}
}
