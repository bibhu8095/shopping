package kart.shopping.orderservice.service;

import java.util.List;

import kart.shopping.orderservice.dto.OrderRequest;
import kart.shopping.orderservice.model.Order;

public interface OrderService {

	public List<Order> listOrders(Long userId);
	public Order createOrder(OrderRequest dto);
	public Order getOrderById(Long id);
		
	
}
