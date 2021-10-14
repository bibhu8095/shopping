package kart.shopping.paymentservice.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.service.PaymentService;

@Component
public class PaymentConsumer {

	private static final Logger Logger = LoggerFactory.getLogger(PaymentConsumer.class);
	
	@Autowired
	PaymentService paymentService;
	
	@RabbitListener(queues="${queue.name}")
	public void consumeMessage(Payment payment) {
		Logger.info("Consumer received a payment request");
		System.out.println(payment);
		paymentService.savePayment(payment);
		Logger.info("Payment details saved");
	}
}
