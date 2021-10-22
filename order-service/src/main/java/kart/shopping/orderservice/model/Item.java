
package kart.shopping.orderservice.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "items")
public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 535437901999561056L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;

	@Column(name = "item_name")
	@NotNull
	private String itemName;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	@NotNull
	private Double price;

	@Column(name = "stock")
	@NotNull
	private Double stock;

	@OneToMany(mappedBy = "id.item", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
	
	public Item() {
		super();
	}

	public Item(String itemName, String description, Double price, Double stock) {
		super();
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", description=" + description + ", price=" + price
				+ ", stock=" + stock + "]";
	}
}
