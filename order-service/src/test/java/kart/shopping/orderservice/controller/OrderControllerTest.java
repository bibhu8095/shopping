package kart.shopping.orderservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import javax.servlet.http.Cookie;

import static org.mockito.Matchers.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;

import kart.shopping.orderservice.Repository.UserRepository;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.model.User;
import kart.shopping.orderservice.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
	
    private static final Long USER_ID = 1L;
    private static final String USER_NAME = "Test";
    
    @MockBean
	private OrderService orderService;
    
    @Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
    private MockMvc mockMvc;
	@Autowired
	private UserRepository userRepository;
	
	@Before()
	public void setup()
	{
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void save_order_controller_should_return201_when_valid_request() throws Exception {
		//given
		User user = new User();
		user.setUserName(USER_NAME);
				
		Item item = new Item();
		item.setItemName("NameItem");
		item.setDescription("Description");
		Order order = new Order();
		order.setUser(user);
		order.setStatus("Ordered");
		
		
	
		//when
		when(userRepository.getById(USER_ID)).thenReturn(user);
		when(orderService.saveOrder(new Order())).thenReturn(order);
		
		//then
		/*
		 * mockMvc.perform(post("{/order/userId}",USER_ID)
		 * .contentType(MediaType.APPLICATION_JSON_UTF8) .content(json) .andReturn());
		 */

		verify(orderService, times(1)).saveOrder(any(Order.class));
		verifyNoMoreInteractions(orderService);
	}
}
