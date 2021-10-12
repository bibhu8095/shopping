
package kart.shopping.orderservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kart.shopping.orderservice.DTO.OrderItemDto;
import kart.shopping.orderservice.DTO.OrderRequest;
import kart.shopping.orderservice.DTO.PaymentDto;
import kart.shopping.orderservice.Exception.OrderNotFoundException;
import kart.shopping.orderservice.Repository.ItemRepository;
import kart.shopping.orderservice.Repository.OrderItemRepository;
import kart.shopping.orderservice.Repository.OrderRepository;
import kart.shopping.orderservice.Repository.UserRepository;
import kart.shopping.orderservice.model.Address;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.model.OrderItem;
import kart.shopping.orderservice.model.User;

@Service
public class OrderService {

	@Value("${topic.exchange}")
	private String exchange;

	@Value("${routing.key}")
	private String routingKey;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepoitory;

	public List<Order> listOrders(Long userId) {
		return orderRepository.findByUserId(userId);
	}

	public Order getOrder(Long orderId) throws OrderNotFoundException {
		Optional<Order> order = orderRepository.findById(orderId);
		if (order.isPresent()) {
			return order.get();
		}
		return new Order();

	}

	public ResponseEntity<Object> getOrderById(Long id) {
		try {
			Order order = getOrder(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Order> createOrder(OrderRequest dto) {
		Optional<User> user = userRepository.findById(dto.getUserId());
		if (user.isPresent()) {
			Order order = this.saveOrder(dto);
			System.out.println("Order created");
			try {
				System.out.println("Order saved");
				return new ResponseEntity<>(order, HttpStatus.CREATED);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private Order saveOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setUserId(orderRequest.getUserId());
		order.setDescription(orderRequest.getDescription());
		order.setStatus(orderRequest.getStatus());
		order.setOrderedDate(LocalDate.now());
		order = orderRepository.save(order);
		saveOrderItems(orderRequest,order);
		return order;

	}

	private void saveOrderItems(OrderRequest orderRequest, Order order) {
		
		Map<Long, Item> itemMap = itemRepository
				.findAllById(orderRequest.getItems().stream().map(OrderItemDto::getItemId).collect(Collectors.toList()))
				.stream().collect(Collectors.toMap(Item::getItemId, itm->itm));
		List<OrderItem> itemlist = new ArrayList<>();
		PaymentDto paymentDto = new PaymentDto(order.getOrderId(),order.getStatus(),0.0,order.getPaymentType());
		orderRequest.getItems().forEach(itm->{
			OrderItem orderItem = new OrderItem(order.getOrderId(),itm.getItemId(),itm.getQuantity());
			itemlist.add(orderItem);
			Item item = itemMap.get(itm.getItemId());
			paymentDto.setPrice(paymentDto.getPrice()+item.getPrice()*itm.getQuantity());
		});
		orderItemRepoitory.saveAll(itemlist);
		rabbitTemplate.convertAndSend(exchange, routingKey,paymentDto);
		
	}
}
