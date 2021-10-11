package kart.shopping.paymentservice.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kart.shopping.paymentservice.entities.Payment;
import kart.shopping.paymentservice.rabbitmq.config.RabbitConfig;

@RestController
@RequestMapping("/rabbit")
public class RabbitProduceTest {

	@Autowired
	private AmqpTemplate template;
	
	@PostMapping
	public String placeOrder(@RequestBody Payment  payment ) {
		System.out.println("executing placeOrder");
		template.convertAndSend(RabbitConfig.exchange_name, RabbitConfig.routing_key, payment);
		System.out.println("completed placeOrder");
		return "done placeOrder";
	}
	
}
