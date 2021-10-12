package kart.shopping.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kart.shopping.paymentservice.entity.Address;
import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.service.PaymentService;

@RestController
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@PostMapping("/savePaymentDetails")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Payment createPayment(@RequestParam("orderId") int orderId, @RequestParam("price") double price,
			@RequestParam("paymentType") String paymentType, @RequestParam("shippingAddress") Address shippingAddress) {
		Payment payment = paymentService.savePaymentDetails(orderId, price, paymentType, shippingAddress);
		return payment;

	}

}
