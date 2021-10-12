package kart.shopping.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kart.shopping.orderservice.model.Address;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.model.User;
import kart.shopping.orderservice.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/all")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping(value = "/create")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PostMapping(value = "/create-address")
	public List<Address> createUserAddress(@RequestBody List<Address> Address) {
		return userService.createUserAddress(Address);
	}
	

}
