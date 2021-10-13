package kart.shopping.orderservice.controllerTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kart.shopping.orderservice.Enum.PaymentType;
import kart.shopping.orderservice.controller.OrderController;
import kart.shopping.orderservice.dto.OrderRequest;
import kart.shopping.orderservice.implservice.OrderServiceImpl;
import kart.shopping.orderservice.model.Order;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = OrderController.class)
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private OrderServiceImpl orderService;
	
	private WebApplicationContext wc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}

	@Test
	public void testGetAllOrders() throws Exception {
		//fail("Not yet implemented");
		Order order = new Order();
		order.setOrderId(1L);
		order.setUserId(2L);
		order.setOrderedDate(LocalDate.now());
		order.setDescription("Best Product");
		order.setStatus("Created");
		order.setPaymentType(PaymentType.UPI);
		
		Order order1 = new Order();
		order1.setOrderId(2L);
		order1.setUserId(3L);
		order1.setOrderedDate(LocalDate.now());
		order1.setDescription("Best Product1");
		order1.setStatus("Created");
		order1.setPaymentType(PaymentType.DebitCards);
		
		List<Order> orderList = new ArrayList<>();
		orderList.add(order);
		orderList.add(order1);
		Mockito.when(orderService.listOrders(order1.getUserId())).thenReturn(orderList);
		String URI = "/order/";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = this.mapToJson(order);
		String atual = result.getResponse().getContentAsString();
		assertEquals(expected, atual);
		
		
		
		
		
	}

	@Test
	public void testGetOrderById() throws Exception {
		//fail("Not yet implemented");
		Order order = new Order();
		order.setOrderId(1L);
		order.setUserId(2L);
		order.setOrderedDate(LocalDate.now());
		order.setDescription("Best Product");
		order.setStatus("Created");
		order.setPaymentType(PaymentType.UPI);
	   
		Mockito.when(orderService.getOrderById(Mockito.any())).thenReturn(order);
		String URI = "/order/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = this.mapToJson(order);
		String atual = result.getResponse().getContentAsString();
		assertEquals(expected, atual);
		
	}

	@Test
	public void testSaveOrder() throws Exception {
		//fail("Not yet implemented");
		
		Order order = new Order();
		order.setOrderId(1L);
		order.setUserId(2L);
		order.setOrderedDate(LocalDate.now());
		order.setDescription("Best Product");
		order.setStatus("Created");
		order.setPaymentType(PaymentType.UPI);
		
		OrderRequest orderReuest = new OrderRequest();
		orderReuest.setUserId(1L);
		orderReuest.setDescription("best one");
		orderReuest.setStatus("created");
		orderReuest.setPaymentType(PaymentType.NetBanking);
		String expected = this.mapToJson(order);
		String URI = "/order/save";
		//Mockito.when(orderService.createOrder(Mockito.any(Order.class))).thenReturn(order);
		Mockito.when(orderService.createOrder(Mockito.any(OrderRequest.class))).thenReturn(order);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(expected)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String atual = response.getContentAsString();
		assertEquals(expected, atual);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	//maps an object into a JSON String Uses a jockson Object mapper 
	
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);	
	}
	
}
