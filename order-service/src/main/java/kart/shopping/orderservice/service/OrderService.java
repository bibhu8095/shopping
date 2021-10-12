
package kart.shopping.orderservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kart.shopping.orderservice.DTO.PaymentDto;
import kart.shopping.orderservice.Exception.OrderNotFoundException;
import kart.shopping.orderservice.Repository.OrderRepository;
import kart.shopping.orderservice.model.Address;
import kart.shopping.orderservice.model.Order;

@Service
public class OrderService {

	@Value("${topic.exchange}")
	private String exchange;



	@Value("${routing.key}")
	private String routingKey;

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private OrderRepository orderRepository;

	public List<Order> listOrders(int userid) {
		List<Order> orderList = orderRepository.findAll();
		return orderList;
	}

	public Order getOrder(Integer orderId) throws OrderNotFoundException {
		Optional<Order> order = orderRepository.findById(orderId);
		if (order.isPresent()) {
			return order.get();
		}
		return new Order();

	}

	public Order saveOrder(Order order) {
		
	 order = orderRepository.save(order);
	 PaymentDto dto=new PaymentDto(order.getOrderId(),new Address(1L, "aabc", "nvnv", "iri"),order.getStatus(),2000.0f,order.getType());
	 rabbitTemplate.convertAndSend(exchange, routingKey,dto);
	 return order;
	 
		
	}
}
