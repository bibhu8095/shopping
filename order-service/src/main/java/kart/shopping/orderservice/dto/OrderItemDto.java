package kart.shopping.orderservice.dto;

public class OrderItemDto {

	private Long itemId;
	private Long quantity;
	
	
	public OrderItemDto(Long itemId, Long quantity) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
