package kart.shopping.paymentservice.util;

import kart.shopping.paymentservice.entity.Address;
import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.entity.PaymentType;

public class EntityUtil {

	public static Payment getPayment() {
		Address shippingAddess = new Address(11,"11A Street, Rome", "001122");
		Payment inputPayment = new Payment(11L, 12.5, PaymentType.DebitCards, shippingAddess);
		inputPayment.setId(1);
		shippingAddess.setId(1);
		return inputPayment;
	}
	
}
