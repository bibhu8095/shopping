package kart.shopping.orderservice.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import kart.shopping.orderservice.Enum.PaymentType;
import kart.shopping.orderservice.Repository.ItemRepository;
import kart.shopping.orderservice.Repository.OrderRepository;
import kart.shopping.orderservice.Repository.UserRepository;
import kart.shopping.orderservice.dto.OrderItemDto;
import kart.shopping.orderservice.dto.OrderRequest;
import kart.shopping.orderservice.implservice.OrderServiceImpl;
import kart.shopping.orderservice.model.Address;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.model.OrderItem;
import kart.shopping.orderservice.model.User;

@SpringBootTest
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
		User user = new User(1L, "testUser", "999999999", "testuser@gamil.com",
				Stream.of(new Address(1L, "KLA", "897789", "HOME"))
				.collect(Collectors.toList()));
		userRepository.save(user);
		
		List<Item> itemList = Stream
				.of(new Item(1L, "BOOK", "Notebook", 55.0, 100.0), new Item(1L, "BOOK", "Notebook", 55.0, 100.0))
				.collect(Collectors.toList());
		itemRepository.saveAll(itemList);
		
		Order order = new Order(1L, 1L,null, "Best Product", "Created",PaymentType.UPI);
		orderRepository.save(order);
		
		
		
	}

	@Test
	void testListOrders() {

		List<OrderItem> orderItemList1 = Stream
				.of(new OrderItem(new Order(1L, 2L, null, "Best Product", "Created", PaymentType.UPI),
						new Item(1L, "BOOK", "Notebook", 55.0, 100.0), 3L))
				.collect(Collectors.toList());

		List<OrderItem> orderItemList2 = Stream
				.of(new OrderItem(new Order(2L, 2L, null, "Best Product", "Created", PaymentType.UPI),
						new Item(1L, "BOOK", "Notebook", 55.0, 100.0), 3L))
				.collect(Collectors.toList());

		Order order1 = new Order(1L, 2L, null, "Best Product", "Created", PaymentType.UPI);
		order1.setOrderItems(orderItemList1);
		Order order2 = new Order(2L, 2L, null, "Best Product", "Created", PaymentType.UPI);
		order1.setOrderItems(orderItemList2);

		List<Order> orderList = Stream.of(order1, order2).collect(Collectors.toList());

		Mockito.when(orderRepository.findByUserId(2L)).thenReturn(orderList);
		assertEquals(orderList, orderService.listOrders(2L));

	}

	@Test
	void testGetOrderById() {
		
		List<OrderItem> orderItemList = Stream
				.of(new OrderItem(new Order(1L, 2L,null, "Best Product", "Created",PaymentType.UPI), new Item(1L, "BOOK", "Notebook", 55.0, 100.0), 3L)).collect(Collectors.toList());

		Order order = new Order(1L, 2L,null, "Best Product", "Created",PaymentType.UPI);
		order.setOrderItems(orderItemList);
		
		Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
		assertEquals(order,orderService.getOrderById(1L));
	}

	@Test
	void testCreateOrder() {
		
		User user = new User(2L, "testUser", "999999999", "testuser@gamil.com",
				Stream.of(new Address(1L, "KLA", "897789", "HOME"))
				.collect(Collectors.toList()));
		
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
		
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        Mockito.when(orderRepository.save(order)).thenReturn(order);
//		Mockito.when(orderService.saveOrder(orderReuest,user)).thenReturn(order);
//		assertThat(orderService.createOrder(order)).isEqualTo(order);
        Order orderOut = orderService.createOrder(orderReuest);
		assertEquals(order, orderOut);
	}

}
