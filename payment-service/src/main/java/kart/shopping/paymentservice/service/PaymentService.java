package kart.shopping.paymentservice.service;

import java.util.Optional;

import kart.shopping.paymentservice.entity.Address;
import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.entity.PaymentType;

public interface PaymentService {

	//public Payment savePaymentDetails(Payment paymentDetails);
	
	public Optional<Payment> savePayment(Payment payment);
	
	public Optional<Payment> savePaymentDetails(Long orderId, double price, PaymentType paymentType, Address shippingAddress);
}
