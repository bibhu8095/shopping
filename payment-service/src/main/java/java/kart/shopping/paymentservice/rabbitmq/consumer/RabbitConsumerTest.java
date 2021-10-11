package kart.shopping.paymentservice.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kart.shopping.paymentservice.entities.Payment;
import kart.shopping.paymentservice.rabbitmq.config.RabbitConfig;
import kart.shopping.paymentservice.service.PaymentService;

@Component
public class RabbitConsumerTest {
	
	@Autowired
	PaymentService paymentService;
	
	@RabbitListener(queues=RabbitConfig.queue_name)
	public void consumeMessage(Payment payment) {
		System.out.println("Consumer received a payment request");
		System.out.println(payment);
		paymentService.savePayment(payment);
		System.out.println("Payment details saved");
	}
	

}
