
package kart.shopping.orderservice.implservice;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kart.shopping.orderservice.Exception.OrderNotFoundException;
import kart.shopping.orderservice.Exception.UserNotFoundException;
import kart.shopping.orderservice.Repository.ItemRepository;
import kart.shopping.orderservice.Repository.OrderItemRepository;
import kart.shopping.orderservice.Repository.OrderRepository;
import kart.shopping.orderservice.Repository.UserRepository;
import kart.shopping.orderservice.dto.OrderItemDto;
import kart.shopping.orderservice.dto.OrderRequest;
import kart.shopping.orderservice.dto.PaymentDto;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.model.OrderItem;
import kart.shopping.orderservice.model.User;
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
		return orderRepository.findByUserId(userId);
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order id is not found"));

	}

	@Override
	public Order createOrder(OrderRequest dto) {

		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new UserNotFoundException("User id is not found"));

		Order order = saveOrder(dto, user);
		//logger.info("Order Saved"  + dto.getUserId());
		logger.info("Order is saved");
		return order;

	}

	private Order saveOrder(OrderRequest orderRequest, User user) {
		
		Order order = new Order();
		order.setUserId(user.getUserId());
		order.setDescription(orderRequest.getDescription());
		order.setStatus(orderRequest.getStatus());
		order.setPaymentType(orderRequest.getPaymentType());
		order.setOrderedDate(LocalDate.now());

		// get items from the items repository since we need to get the price for each item
		Map<Long, Item> itemMap = itemRepository
				.findAllById(orderRequest.getItems().stream().map(OrderItemDto::getItemId).collect(Collectors.toList()))
				.stream().collect(Collectors.toMap(Item::getItemId, itm -> itm));
		
		order.setOrderItems(of(order, itemMap, orderRequest.getItems()));

		order = orderRepository.save(order);
		pushToQueue(order);
		return order;

	}
	
	private List<OrderItem> of(Order order,Map<Long, Item> itemMap, List<OrderItemDto> orderItemDtos) {
		return orderItemDtos.stream().map(orderItemDto -> 
			new OrderItem(order, itemMap.get(orderItemDto.getItemId()), orderItemDto.getQuantity())).collect(Collectors.toList());
	
	}
	
	private void pushToQueue(Order order) {

		
		PaymentDto paymentDto = new PaymentDto(order.getOrderId(), order.getStatus(), order.getTotalPrice(), order.getPaymentType());
		
		rabbitTemplate.convertAndSend(exchange, routingKey, paymentDto);

	}
}

