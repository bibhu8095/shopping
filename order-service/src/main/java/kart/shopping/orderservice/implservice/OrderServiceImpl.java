
package kart.shopping.orderservice.implservice;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kart.shopping.orderservice.dto.OrderItemDto;
import kart.shopping.orderservice.dto.OrderRequest;
import kart.shopping.orderservice.dto.PaymentDto;
import kart.shopping.orderservice.exception.OrderNotFoundException;
import kart.shopping.orderservice.exception.OsDataNotFoundException;
import kart.shopping.orderservice.model.Address;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.model.OrderItem;
import kart.shopping.orderservice.model.User;
import kart.shopping.orderservice.repository.ItemRepository;
import kart.shopping.orderservice.repository.OrderRepository;
import kart.shopping.orderservice.repository.UserRepository;
import kart.shopping.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

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
	
	@Override
	public List<Order> listOrders(Long userId) {
		if(userId==null) {
			throw new OsDataNotFoundException("User id must not be null");
		}
		return orderRepository.findByUserId(userId);
	}

	@Override
	public Order getOrderById(Long orderId) {
		if(orderId==null) {
			throw new OsDataNotFoundException("Order id must not be null");
		}
		return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order id is not found"));

	}

	@Override
	@Transactional
	public Order createOrder(OrderRequest order) {

		if(order==null) {
			throw new OsDataNotFoundException("Order Request must not be null");
		}
		Optional<User> user =userRepository.findById(order.getUserId());
		if(!user.isPresent()) {
			throw new OsDataNotFoundException("User not found");
		}
	
		logger.info("Order is prepare to save");
		Order orderResponse = saveOrder(order);
		logger.info("Order is saved");
		
		return orderResponse;

	}
	
	private Order saveOrder(OrderRequest orderRequest) {
				
		Order order = new Order();
		order.setUserId(orderRequest.getUserId());
		order.setDescription(orderRequest.getDescription());
		order.setStatus(orderRequest.getStatus());
		order.setPaymentType(orderRequest.getPaymentType());
		order.setOrderedDate(LocalDate.now());

		// get items from the items repository since we need to get the price for each item
		Map<Long, Item> itemMap = itemRepository
				.findAllById(orderRequest.getItems().stream().map(OrderItemDto::getItemId).collect(Collectors.toList()))
				.stream().collect(Collectors.toMap(Item::getItemId, itm -> itm));
		
		if(itemMap.isEmpty()) {
			throw new OsDataNotFoundException("Order not containing any valid items");
		}
		
		order.setOrderItems(of(order, itemMap, orderRequest.getItems()));

		order = orderRepository.save(order);
		
		pushToQueue(order,orderRequest.getShippingAddress());
		return order;

	}

	private List<OrderItem> of(Order order,Map<Long, Item> itemMap, List<OrderItemDto> orderItemDtos) {
		return orderItemDtos.stream().map(orderItemDto -> 
			new OrderItem(order, itemMap.get(orderItemDto.getItemId()), orderItemDto.getQuantity())).collect(Collectors.toList());
	
	}
	
	private void pushToQueue(Order order, Address address) {

		PaymentDto paymentDto = new PaymentDto(order.getOrderId(), order.getStatus(), order.getTotalPrice(), order.getPaymentType(),address);
		rabbitTemplate.convertAndSend(exchange, routingKey, paymentDto);
		logger.info("Payment data pushed into queue");

	}
	
}

