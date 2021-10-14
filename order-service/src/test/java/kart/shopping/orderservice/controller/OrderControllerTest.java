
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

import kart.shopping.orderservice.Enum.PaymentType;
import kart.shopping.orderservice.dto.OrderItemDto;
import kart.shopping.orderservice.dto.OrderRequest;
import kart.shopping.orderservice.implservice.OrderServiceImpl;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.model.OrderItem;

@SpringBootTest
class OrderControllerTest {
	private MockMvc mockMvc;
	@MockBean
	private OrderServiceImpl orderService;
	@Autowired
	private WebApplicationContext wc;

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
		
		Order order1 = new Order(1L, 2L, null, "Best Product", "Created", PaymentType.UPI);
		List<OrderItem> orderItemList1 = Stream
				.of(new OrderItem(order1,
						new Item(1L, "BOOK", "Notebook", 55.0, 100.0), 3L))
				.collect(Collectors.toList());
		order1.setOrderItems(orderItemList1);
		
		Order order2 = new Order(2L, 2L, null, "Best Product", "Created", PaymentType.UPI);
		List<OrderItem> orderItemList2 = Stream
				.of(new OrderItem(order2,
						new Item(1L, "BOOK", "Notebook", 55.0, 100.0), 3L))
				.collect(Collectors.toList());
		order2.setOrderItems(orderItemList2);

		List<Order> orderList = Stream.of(order1, order2).collect(Collectors.toList());
		Mockito.when(orderService.listOrders(2L)).thenReturn(orderList);
	}

	@Test
	void testGetAllOrders() throws Exception {
		
		Order order1 = new Order(1L, 2L, null, "Best Product", "Created", PaymentType.UPI);
		List<OrderItem> orderItemList1 = Stream
				.of(new OrderItem(order1,
						new Item(1L, "BOOK", "Notebook", 55.0, 100.0), 3L))
				.collect(Collectors.toList());
		order1.setOrderItems(orderItemList1);
		
		Order order2 = new Order(2L, 2L, null, "Best Product", "Created", PaymentType.UPI);
		List<OrderItem> orderItemList2 = Stream
				.of(new OrderItem(order2,
						new Item(1L, "BOOK", "Notebook", 55.0, 100.0), 3L))
				.collect(Collectors.toList());
		order2.setOrderItems(orderItemList2);

		List<Order> orderList = Stream.of(order1, order2).collect(Collectors.toList());
		
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
		
		Order order = new Order(1L, 2L, null, "Best Product", "Created", PaymentType.UPI);
		List<OrderItem> orderItemList = Stream
				.of(new OrderItem(order,
						new Item(1L, "BOOK", "Notebook", 55.0, 100.0), 3L))
				.collect(Collectors.toList());
		order.setOrderItems(orderItemList);
		
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

		Order order = new Order(1L, 2L, null, "Best Product", "Created", PaymentType.UPI);
		List<OrderItem> orderItemList = Stream
				.of(new OrderItem(order,
						new Item(1L, "BOOK", "Notebook", 55.0, 100.0), 3L))
				.collect(Collectors.toList());
		order.setOrderItems(orderItemList);
		
		OrderRequest orderReuest = new OrderRequest(2L, "best one", "created", PaymentType.NetBanking);
		List<OrderItemDto> orderItemDtoList = Stream
				.of(new OrderItemDto(1L, 3L))
				.collect(Collectors.toList());
		orderReuest.setItems(orderItemDtoList);
		
		String expected = this.mapToJson(order);
		String URI = "/order/save";
		Mockito.when(orderService.createOrder(Mockito.any(OrderRequest.class))).thenReturn(order);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(expected).contentType(MediaType.APPLICATION_JSON);
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
