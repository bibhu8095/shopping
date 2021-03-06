
package kart.shopping.orderservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kart.shopping.orderservice.dto.PaymentType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = -7652888985305782769L;

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

	@OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL)
	@NotNull
	@JsonIgnore
	private List<OrderItem> orderItems;

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

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", orderedDate=" + orderedDate + ", description="
				+ description + ", status=" + status + ", paymentType=" + paymentType + "]";
	}

	public Double getTotalPrice() {
		return orderItems.stream().collect(Collectors.summingDouble(OrderItem::getTotalPricePerItem));
	}

}
