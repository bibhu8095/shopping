package kart.shopping.orderservice.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import kart.shopping.orderservice.implservice.UserServiceImpl;
import kart.shopping.orderservice.model.User;
import kart.shopping.orderservice.repository.UserRepository;
import kart.shopping.orderservice.util.EntityUtil;

@SpringBootTest
class UserServiceImplTest {
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@MockBean
	private UserRepository userRepository;
	
	@Test
	void testCreateUser() throws Exception{
		User user = EntityUtil.getUser();
		Mockito.when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userServiceImpl.createUser(user));
	}

	@Test
	void testGetAllUser() throws Exception{
		List<User> userList = EntityUtil.getUserList();
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		assertEquals(2, userServiceImpl.getAllUsers().size());
	}

}
