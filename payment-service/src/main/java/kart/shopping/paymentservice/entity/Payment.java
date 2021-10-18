package kart.shopping.paymentservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "order_id")
	@NotNull
	//@NotEmpty(message = "orderId can not be empty")
	private Long orderId;

	@Column(name = "price")
	@NotNull(message = "Price can not be null")
	//@NotEmpty(message = "price can not be empty")
	private double price;

	@Column(name = "payment_type")
	@NotNull
	//@NotEmpty(message = "paymentType can not be empty")
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "addressId")
	@NotNull
	private Address shippingAddress;

	public Payment() {
		super();
	}

	public Payment(Long orderId, double price,
			PaymentType paymentType, Address shippingAddress) {
		super();
		this.orderId = orderId;
		this.price = price;
		this.paymentType = paymentType;
		this.shippingAddress = shippingAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", orderId=" + orderId + ", price=" + price + ", paymentType=" + paymentType
				+ ", shippingAddress=" + shippingAddress + "]";
	}

}
