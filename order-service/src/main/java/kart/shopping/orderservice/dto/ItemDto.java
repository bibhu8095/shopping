package kart.shopping.orderservice.dto;

public class ItemDto {

	private String itemName;
	private String description;
	private Double price;
	private Double stock;

	public ItemDto() {
		super();
	}

	public ItemDto(String itemName, String description, Double price, Double stock) {
		super();
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

}
