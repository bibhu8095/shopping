
package kart.shopping.orderservice.model;

import javax.persistence.*;

import com.sun.istack.NotNull;

import kart.shopping.orderservice.Enum.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@NotNull
	private User user;

	@Column(name = "ordered_date")
	@NotNull
	private LocalDate orderedDate;

	@Column(name = "description")
	@NotNull
	private String description;

	@Column(name = "status")
	@NotNull
	private String status;

	@Enumerated(EnumType.STRING)
	private PaymentType type;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(LocalDate orderedDate) {
		this.orderedDate = orderedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((orderedDate == null) ? 0 : orderedDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderedDate == null) {
			if (other.orderedDate != null)
				return false;
		} else if (!orderedDate.equals(other.orderedDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type != other.type)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", user=" + user + ", orderedDate=" + orderedDate + ", description="
				+ description + ", status=" + status + ", type=" + type + "]";
	}

	public Order() {
		super();
	}

	public Order(Long orderId, User user, LocalDate orderedDate, String description, String status, PaymentType type) {
		super();
		this.orderId = orderId;
		this.user = user;
		this.orderedDate = orderedDate;
		this.description = description;
		this.status = status;
		this.type = type;
	}
	
	
	
	
	/*
	 * @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	 * 
	 * @JoinColumn(name = "order_id",referencedColumnName = "id",insertable =
	 * false,updatable = false) private List<OrderItem> orderItems;
	 * 
	 * @ManyToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "user_id", referencedColumnName = "id", insertable =
	 * false, updatable = false) private User user1;
	 */
	/*
	 * private Address addressId;
	 * 
	 * @ManyToMany private List<Item> item;
	 * 
	 * @ManyToMany (cascade = CascadeType.ALL)
	 * 
	 * @JoinTable (name = "orders" , joinColumns = @JoinColumn(name = "order_id"),
	 * inverseJoinColumns = @JoinColumn (name = "item_id")) private List<Item>
	 * items;
	 */

}
