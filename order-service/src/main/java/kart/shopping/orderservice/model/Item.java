
package kart.shopping.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@JsonIgnore
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

	public Item() {
		super();
	}

	public Item(Long itemId, String itemName, String description, Double price, Double stock) {
		super();
		this.itemId = itemId;
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

	/*
	 * @Column(name = "quantity")
	 * 
	 * @NonNull private int quantity;
	 * 
	 * @ManyToMany(mappedBy = "items")
	 * 
	 * @JsonIgnore private List<Order> orders;
	 */

}
