package kart.shopping.orderservice.controller;

import java.util.List;

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

import kart.shopping.orderservice.DTO.OrderRequest;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam Long userId) {
		return new ResponseEntity<List<Order>>(orderService.listOrders(userId), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getOrderById(@PathVariable("id") Long id) {
		return orderService.getOrderById(id);

	}

	@PostMapping(value = "/save")
	public ResponseEntity<Order> saveOrder(@RequestBody OrderRequest dto) {
		return orderService.createOrder(dto);

	}

}
