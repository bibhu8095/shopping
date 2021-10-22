package kart.shopping.orderservice.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import kart.shopping.orderservice.dto.OrderItemDto;
import kart.shopping.orderservice.dto.OrderRequest;
import kart.shopping.orderservice.dto.PaymentType;
import kart.shopping.orderservice.model.Address;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.model.Order;
import kart.shopping.orderservice.model.OrderItem;
import kart.shopping.orderservice.model.User;

public class EntityUtil {
	
	public static User getUser() {
		return new User(2L, "testUser", "999999999", "testuser1@gamil.com",
				Stream.of(new Address(1L, "KLA", "927789", "HOME"))
				.collect(Collectors.toList()));
	}
	
	public static List<User> getUserList(){
		return Stream.of(new User(1L, "testUser", "999999998", "testuser@gamil.com",
				Stream.of(new Address(1L, "KLA", "847789", "HOME"))
				.collect(Collectors.toList())),new User(2L, "testUser2", "999999997", "testuser1@gamil.com",
						Stream.of(new Address(1L, "KLA", "898989", "HOME"))
						.collect(Collectors.toList()))).collect(Collectors.toList());
	}
	
	public static List<Item> getItemList(){
		return Stream
				.of(new Item("BOOK", "Note book", 55.0, 100.0), new Item("Pen", "Pen", 20.0, 100.0))
				.collect(Collectors.toList());
	}

	public static List<Order> getOrderList(){
		
		Order order1 = new Order(1L, 2L, null, "Best Product", "Created", PaymentType.UPI);
		List<OrderItem> orderItemList1 = Stream
				.of(new OrderItem(order1,
						new Item("BOOK", "Notebook", 55.0, 100.0), 3L))
				.collect(Collectors.toList());
		order1.setOrderItems(orderItemList1);
		
		Order order2 = new Order(2L, 2L, null, "Best Product", "Created", PaymentType.UPI);
		List<OrderItem> orderItemList2 = Stream
				.of(new OrderItem(order2,
						new Item("BOOK", "Notebook", 55.0, 100.0), 3L))
				.collect(Collectors.toList());
		order2.setOrderItems(orderItemList2);
		
		return Stream.of(order1, order2).collect(Collectors.toList());
	}
	
	public static Order getOrder() {
		Order order = new Order(1L, 2L, null, "Best Product", "Created", PaymentType.UPI);
		List<OrderItem> orderItemList = Stream
				.of(new OrderItem(order,
						new Item("BOOK", "Notebook", 55.0, 100.0), 3L))
				.collect(Collectors.toList());
		order.setOrderItems(orderItemList);
		return order;
	}
	
	public static OrderRequest getOrderRequest() {
		OrderRequest orderReuest = new OrderRequest(2L, "best one", "Created", PaymentType.NET_BANKING);
		List<OrderItemDto> orderItemDtoList = Stream.of(new OrderItemDto(1L, 3L)).collect(Collectors.toList());
		orderReuest.setItems(orderItemDtoList);
		orderReuest.setShippingAddress(new Address(1L, "KLA", "897789", "HOME"));
		return orderReuest;
	}
	
	public static List<Item> getItemListWithId(){
		List<Item> itemList  = new ArrayList<>();
		Item item1 = new Item("BOOK", "Note book", 55.0, 100.0);
		item1.setItemId(1L);
		itemList.add(item1);
		return itemList;
	}
	
	private EntityUtil() {
	    throw new IllegalStateException("Utility class");
	  }

}
