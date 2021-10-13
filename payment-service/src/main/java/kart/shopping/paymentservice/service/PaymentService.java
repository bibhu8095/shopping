package kart.shopping.paymentservice.service;

import kart.shopping.paymentservice.entity.Address;
import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.entity.PaymentType;

public interface PaymentService {

	//public Payment savePaymentDetails(Payment paymentDetails);
	
	public void savePayment(Payment payment);
	
	public Payment savePaymentDetails(Long orderId, double price, PaymentType paymentType, Address shippingAddress);
}
