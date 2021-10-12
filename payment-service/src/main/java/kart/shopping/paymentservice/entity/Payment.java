package kart.shopping.paymentservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "order_id")
	private int orderId;

	@Column(name = "price")
	private double price;

	@Column(name = "payment_type")
	private String paymentType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "addressId")
	private Address shippingAddress;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int id, int orderId, double price, String paymentType, Address shippingAddress) {
		super();
		this.id = id;
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
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
