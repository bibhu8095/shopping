package kart.shopping.paymentservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.entity.PaymentDto;
import kart.shopping.paymentservice.service.PaymentService;

@RestController
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@PostMapping("/savePaymentDetails")
	@ResponseStatus(code = HttpStatus.CREATED)
	public @Valid Payment createPayment(@RequestBody Payment paymentDto) {
		Payment payment = paymentService.savePaymentDetails(paymentDto.getOrderId(), paymentDto.getPrice(), paymentDto.getPaymentType(), paymentDto.getShippingAddress());
		return payment;

	}

}
