package kart.shopping.orderservice.implservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kart.shopping.orderservice.exception.OsDataNotFoundException;
import kart.shopping.orderservice.model.User;
import kart.shopping.orderservice.repository.UserRepository;
import kart.shopping.orderservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger Logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public List<User> getAllUsers() {
		Logger.info("getting all users");
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		Logger.info("save User start");
		if (user == null) {
			throw new OsDataNotFoundException("User is null");
		}
		user = userRepository.save(user);
		Logger.info("save User done");

		return user;
	}

}
