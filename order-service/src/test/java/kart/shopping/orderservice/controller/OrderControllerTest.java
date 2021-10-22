
package kart.shopping.orderservice.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kart.shopping.orderservice.dto.OrderRequest;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.service.OrderService;
import kart.shopping.orderservice.util.EntityUtil;

@SpringBootTest
class OrderControllerTest {

	private MockMvc mockMvc;
	@MockBean
	private OrderService orderService;
	@Autowired
	private WebApplicationContext wc;

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();

		List<Order> orderList = EntityUtil.getOrderList();
		Mockito.when(orderService.listOrders(2L)).thenReturn(orderList);
	}

	@Test
	void testGetAllOrders() throws Exception {

		List<Order> orderList = EntityUtil.getOrderList();

		Mockito.when(orderService.listOrders(2L)).thenReturn(orderList);
		String URI = "/order/?userId=2";
		String expected = this.mapToJson(orderList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputJson = response.getContentAsString();
		assertEquals(expected, outputJson);
	}

	@Test
	void testGetOrderById() throws Exception {

		Order order = EntityUtil.getOrder();

		Mockito.when(orderService.getOrderById(Mockito.any())).thenReturn(order);
		String URI = "/order/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = this.mapToJson(order);
		String atual = result.getResponse().getContentAsString();
		assertEquals(expected, atual);
	}

	@Test
	void testSaveOrder() throws Exception {

		Order order = EntityUtil.getOrder();
		OrderRequest orderRequest = EntityUtil.getOrderRequest();

		String expected = this.mapToJson(order);
		String orderRequestinput = this.mapToJson(orderRequest);
		System.out.println(expected);
		String URI = "/order/save";
		Mockito.when(orderService.createOrder(Mockito.any(OrderRequest.class))).thenReturn(order);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(orderRequestinput).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String atual = response.getContentAsString();
		assertEquals(expected, atual);
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
