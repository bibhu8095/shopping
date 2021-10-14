package kart.shopping.paymentservice.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.service.PaymentService;

@RestController
public class PaymentController {
	
	private static final Logger Logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentService paymentService;

	@PostMapping("/savePaymentDetails")
	@ResponseStatus(code = HttpStatus.CREATED)
	public @Valid Payment createPayment(@RequestBody Payment paymentDto) {
		Logger.info("Save Payment Details");
		Payment payment = paymentService.savePaymentDetails(paymentDto.getOrderId(), paymentDto.getPrice(), paymentDto.getPaymentType(), paymentDto.getShippingAddress());
		return payment;

	}

}
