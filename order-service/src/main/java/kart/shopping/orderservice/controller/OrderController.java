package kart.shopping.orderservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kart.shopping.orderservice.dto.OrderRequest;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderController {

	Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam Long userId) {
		return new ResponseEntity<>(orderService.listOrders(userId), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
		return new ResponseEntity<>( orderService.getOrderById(id),HttpStatus.OK);

	}

	@PostMapping(value = "/save")
	public ResponseEntity<Order> saveOrder(@RequestBody OrderRequest order) {
//		validations
		return new ResponseEntity<>(orderService.createOrder(order),HttpStatus.CREATED);
	}

}
