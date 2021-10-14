
package kart.shopping.orderservice.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "order_item")

public class OrderItem {

	@EmbeddedId
	private OrderItemPk id;
	
	@Column(name = "quantity")
	@NotNull
	private Long quantity;
	
	public OrderItem() {
		super();
	}

	public OrderItem(Order order, Item item, Long quantity) {
		super();
		id = new OrderItemPk();
		id.setOrder(order);
		id.setItem(item);
		this.quantity = quantity;
	}

	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}

	public Item getItem() {
		return id.getItem();
	}

	public void setItem(Item item) {
		id.setItem(item);
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public Double getTotalPricePerItem() {
		return id.getItem().getPrice() * getQuantity();
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + id.getOrder()+ ", itemId=" + id.getItem() + ", quantity=" + quantity + "]";
	}

	
	
}
