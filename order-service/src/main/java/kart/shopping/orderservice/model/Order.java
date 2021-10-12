
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
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@Column(name = "user_id")
	@NotNull
	private Long userId;

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
	private PaymentType paymentType;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Order() {
		super();
	}

	public Order(Long orderId, Long userId, LocalDate orderedDate, String description, String status,
			PaymentType paymentType) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderedDate = orderedDate;
		this.description = description;
		this.status = status;
		this.paymentType = paymentType;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", orderedDate=" + orderedDate + ", description="
				+ description + ", status=" + status + ", paymentType=" + paymentType + "]";
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
