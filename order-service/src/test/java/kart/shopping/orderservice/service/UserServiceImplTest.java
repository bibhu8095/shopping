package kart.shopping.orderservice.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import kart.shopping.orderservice.Repository.UserRepository;
import kart.shopping.orderservice.implservice.UserServiceImpl;
import kart.shopping.orderservice.model.Address;
import kart.shopping.orderservice.model.User;

@SpringBootTest
class UserServiceImplTest {
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@MockBean
	private UserRepository userRepository;
	
	@Test
	void testCreateUser() throws Exception{
		User user = new User(1L, "testUser", "999999999", "testuser@gamil.com",
				Stream.of(new Address(1L, "KLA", "897789", "HOME"))
				.collect(Collectors.toList()));
		Mockito.when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userServiceImpl.createUser(user));
	}

	@Test
	void testGetAllUser() throws Exception{
		List<User> userList = Stream.of(new User(1L, "testUser", "999999999", "testuser@gamil.com",
				Stream.of(new Address(1L, "KLA", "897789", "HOME"))
				.collect(Collectors.toList())),new User(2L, "testUser2", "999999999", "testuser@gamil.com",
						Stream.of(new Address(1L, "KLA", "897789", "HOME"))
						.collect(Collectors.toList()))).collect(Collectors.toList());
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		assertEquals(2, userServiceImpl.getAllUsers().size());
	}

}
