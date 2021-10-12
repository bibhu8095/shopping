package kart.shopping.paymentservice.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.service.PaymentService;

@Component
public class PaymentConsumer {

	@Autowired
	PaymentService paymentService;
	
	@RabbitListener(queues="${queue.name}")
	public void consumeMessage(Payment payment) {
		System.out.println("Consumer received a payment request");
		System.out.println(payment);
		paymentService.savePayment(payment);
		System.out.println("Payment details saved");
	}
}
