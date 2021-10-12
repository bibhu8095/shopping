package kart.shopping.paymentservice.service;

import kart.shopping.paymentservice.entity.Address;
import kart.shopping.paymentservice.entity.Payment;

public interface PaymentService {

	//public Payment savePaymentDetails(Payment paymentDetails);
	
	public void savePayment(Payment payment);
	
	public Payment savePaymentDetails(int orderId, double price, String paymentType, Address shippingAddress);
}
