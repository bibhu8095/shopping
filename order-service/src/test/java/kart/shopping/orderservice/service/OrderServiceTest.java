package kart.shopping.orderservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kart.shopping.orderservice.Enum.PaymentType;
import kart.shopping.orderservice.Repository.OrderRepository;
import kart.shopping.orderservice.Repository.UserRepository;
import kart.shopping.orderservice.dto.OrderRequest;
import kart.shopping.orderservice.implservice.OrderServiceImpl;
import kart.shopping.orderservice.model.Address;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.model.User;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class OrderServiceTest {

	@Autowired
	private OrderServiceImpl orderService;
	
	@MockBean
	private OrderRepository orderRepository;
	
	@MockBean
	private UserRepository userRepository;

	
	@BeforeEach
	void setUp() throws Exception {
		 User user = new User();
		 user.setUserId(2L);
		 user.setUserName("Abc");
		 user.setEmailId("Abc@gmail.com");
		 user.setPhoneNumber("678998789");
	List<Address> addressList = new ArrayList();
	Address address = new Address(2L,"Hyd","78908","Shipping");
	addressList.add(address);
	user.setAddress(addressList);
	
	Item item = new Item();
	
	}

	@Test
	void testListOrders() {
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
		  order1.setUserId(2L);
		  order1.setOrderedDate(LocalDate.now());
		  order1.setDescription("Best Product"); 
		  order1.setStatus("Created");
		  order1.setPaymentType(PaymentType.UPI);
		  
		  List<Order> orderList = new ArrayList<>(); 
		  orderList.add(order);
		  orderList.add(order1);
		  
		  Mockito.when(orderRepository.findAll()).thenReturn(orderList);
		  //assertEquals(orderService.listOrders(null)).isEqualTo(orderList);
		  assertEquals(2, orderService.listOrders(2L).size());
		 // assertThat(2, is(orderService.listOrders(2L)).size()); 
		  //assertEquals(order, orderList);
		 		
		
	}

	@Test
	void testGetOrderById() {
		//fail("Not yet implemented");
		Order order = new Order();
		order.setOrderId(1L);
		order.setUserId(2L);
		order.setOrderedDate(LocalDate.now());
		order.setDescription("Best Product");
		order.setStatus("Created");
		order.setPaymentType(PaymentType.UPI);
		Mockito.when(orderRepository.getById(1L)).thenReturn(order);
		//assertThat(orderService.getOrderById(1)).isEqualTo(order);
		assertEquals(orderService.getOrderById(1L), order);
	}

	@Test
	void testCreateOrder() {
		//fail("Not yet implemented");
		
		Order order = new Order();
		//order.setOrderId(1L);
		order.setUserId(2L);
		//order.setOrderedDate(LocalDate.now());
		order.setDescription("Best Product");
		order.setStatus("Created");
		order.setPaymentType(PaymentType.UPI);
		
		OrderRequest orderReuest = new OrderRequest();
		orderReuest.setUserId(2L);
		orderReuest.setDescription("best one");
		orderReuest.setStatus("created");
		orderReuest.setPaymentType(PaymentType.NetBanking);

		Mockito.when(orderRepository.save(order)).thenReturn(order);
		//Mockito.when(userRepository.save(user)).thenReturn(user);
		//assertThat(orderService.createOrder(order)).isEqualTo(order);
		assertEquals(order, orderService.createOrder(orderReuest));
	}

}
