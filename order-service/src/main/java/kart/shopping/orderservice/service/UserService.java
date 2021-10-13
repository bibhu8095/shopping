package kart.shopping.orderservice.service;

import java.util.List;

import kart.shopping.orderservice.model.Address;
import kart.shopping.orderservice.model.User;

public interface UserService {
	
	public List<User> getAllUsers();
	public User createUser(User user);

}
