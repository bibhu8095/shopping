package kart.shopping.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kart.shopping.orderservice.Repository.AddressRepository;
import kart.shopping.orderservice.Repository.UserRepository;
import kart.shopping.orderservice.model.Address;
import kart.shopping.orderservice.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public List<Address> createUserAddress(List<Address> address) {
		return addressRepository.saveAll(address);
	}

}
