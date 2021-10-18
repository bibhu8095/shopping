package kart.shopping.orderservice.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import kart.shopping.orderservice.dto.OrderRequest;
import kart.shopping.orderservice.implservice.OrderServiceImpl;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.model.User;
import kart.shopping.orderservice.repository.ItemRepository;
import kart.shopping.orderservice.repository.OrderRepository;
import kart.shopping.orderservice.repository.UserRepository;
import kart.shopping.orderservice.util.EntityUtil;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

	@Autowired
	private OrderServiceImpl orderService;

	@MockBean
	private OrderRepository orderRepository;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private ItemRepository itemRepository;

	@BeforeEach
	void setUp() throws Exception {
		
		User user = EntityUtil.getUser();
		Mockito.when(userRepository.save(user)).thenReturn(user);

		List<Item> itemList = EntityUtil.getItemList();
		Mockito.when(itemRepository.saveAll(itemList)).thenReturn(itemList);

	}

	@Test
	void testListOrders() {

		List<Order> orderList = EntityUtil.getOrderList();

		Mockito.when(orderRepository.findByUserId(2L)).thenReturn(orderList);
		assertEquals(orderList, orderService.listOrders(2L));

	}

	@Test
	void testGetOrderById() {

		Order order = EntityUtil.getOrder();

		Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
		assertEquals(order, orderService.getOrderById(1L));
	}

	@Test
	void testCreateOrder() {

		User user = EntityUtil.getUser();

		Order order = EntityUtil.getOrder();

		OrderRequest orderReuest = EntityUtil.getOrderRequest();
		Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user));
		Mockito.when(orderRepository.save(Mockito.any())).thenReturn(order);
		Order orderOut = orderService.createOrder(orderReuest);
		assertEquals(order, orderOut);
	}

}
