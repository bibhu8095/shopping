package kart.shopping.orderservice.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import kart.shopping.orderservice.model.Address;

public class OrderRequest {

	@NotNull(message = "User id must not be null/blank")
	private Long userId;
	private String description;
	private String status;
	@NotNull(message = "Payment type must not be null/blank")
	private PaymentType paymentType;
	@NotEmpty(message = "Oreder request atlest contain one item")
	private List<OrderItemDto> items;
	@NotNull(message = "you must provide shipping address")
	private Address shippingAddress;

	public OrderRequest() {
		super();
	}

	public OrderRequest(Long userId, String description, String status, PaymentType paymentType) {
		super();
		this.userId = userId;
		this.description = description;
		this.status = status;
		this.paymentType = paymentType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public List<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDto> items) {
		this.items = items;
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

}
