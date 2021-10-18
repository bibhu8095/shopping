package kart.shopping.orderservice.dto;

import kart.shopping.orderservice.model.Address;


public class PaymentDto {

	private Long orderId;
	private Address shippingAddress;
	private String status;
	private Double price;
	private PaymentType paymentType;

	public PaymentDto(Long orderId, String status, Double price, PaymentType paymentType,Address shippingAddress) {
		super();
		this.orderId = orderId;
		this.shippingAddress = shippingAddress;
		this.status = status;
		this.price = price;
		this.paymentType = paymentType;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PaymentDto() {
		super();
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

}
