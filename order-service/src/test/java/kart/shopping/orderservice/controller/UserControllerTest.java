package kart.shopping.orderservice.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import kart.shopping.orderservice.implservice.UserServiceImpl;
import kart.shopping.orderservice.model.Address;
import kart.shopping.orderservice.model.User;

@SpringBootTest
class UserControllerTest {
	
	private MockMvc mockMvc;
	@MockBean
	private UserServiceImpl userService;
	@Autowired
	private WebApplicationContext webAppContest;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContest).build();
	}
	
	@Test
	void testCreateUser() throws Exception{
		User user = new User(1L, "testUser", "999999999", "testuser@gamil.com",
				Stream.of(new Address(1L, "KLA", "897789", "HOME"))
				.collect(Collectors.toList()));
		
		String inputJson = mapper.writeValueAsString(user);
		Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);
		String URI = "/user/create";	
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputJson = response.getContentAsString();
		assertEquals(outputJson, inputJson);
	}

	@Test
	void testGetAllUser() throws Exception{
		List<User> userList = Stream.of(new User(1L, "testUser", "999999999", "testuser@gamil.com",
				Stream.of(new Address(1L, "KLA", "897789", "HOME"))
				.collect(Collectors.toList())),new User(2L, "testUser2", "999999999", "testuser@gamil.com",
						Stream.of(new Address(1L, "KLA", "897789", "HOME"))
						.collect(Collectors.toList()))).collect(Collectors.toList());
		
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		String URI = "/user/all";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = mapper.writeValueAsString(userList);
		String atual = result.getResponse().getContentAsString();
		assertEquals(expected, atual);

	}

}
