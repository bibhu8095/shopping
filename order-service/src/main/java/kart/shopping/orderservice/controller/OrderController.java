package kart.shopping.orderservice.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kart.shopping.orderservice.Exception.OrderNotFoundException;
import kart.shopping.orderservice.Repository.OrderRepository;
import kart.shopping.orderservice.Repository.UserRepository;
import kart.shopping.orderservice.header.HeaderGenerator;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.model.User;
import kart.shopping.orderservice.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository UserRepository;

	@GetMapping(value = "/")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam int userId) {

		List<Order> orderDtoList = orderService.listOrders(userId);
		return new ResponseEntity<List<Order>>(orderDtoList, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getOrderById(@PathVariable("id") Integer id) {

		try {
			Order order = orderService.getOrder(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	} 
	@PostMapping(value= "/order/{userId}")
	public ResponseEntity<Order> saveOrder(@PathVariable("userId") Long userId){
		User user = UserRepository.getById(userId);
		if(user!=null) {
			Order order = this.createOrder(user);
			try {
			orderService.saveOrder(order);
			 return new ResponseEntity<Order>(
             		order, HttpStatus.CREATED);
         }catch (Exception ex){
             ex.printStackTrace();
             return new ResponseEntity<Order>(
             		HttpStatus.INTERNAL_SERVER_ERROR);
         }}
		return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	private Order createOrder(User user) {
		Order order = new Order();
		order.setUser(user);
		order.setDescription("all");
		order.setStatus("created");
		order.setOrderedDate(LocalDate.now());
		return order;
		
	}
}
