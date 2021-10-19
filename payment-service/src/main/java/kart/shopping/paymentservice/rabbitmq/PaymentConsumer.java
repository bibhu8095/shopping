package kart.shopping.paymentservice.rabbitmq;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.service.PaymentService;

@Component
public class PaymentConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);
	
	@Autowired
	PaymentService paymentService;
	
	@RabbitListener(queues="${queue.name}")
	public Optional<Payment> consumePaymentMessage(Payment payment) {
		LOGGER.info("PaymnetConsumer : received a new payment request");
		Optional<Payment> savedPayment = Optional.empty();
		if(payment!=null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		    Validator validator = factory.getValidator();
		    Set<ConstraintViolation<Payment>> violations = validator.validate(payment);
			
		    if(violations.isEmpty()){
		    	LOGGER.info("PaymnetConsumer : no violations");
		    	savedPayment = paymentService.savePayment(payment);
				savedPayment.ifPresentOrElse(payemntValue->
						{LOGGER.info("Payment request processed. Payment : "+payemntValue);},
						()-> {LOGGER.info("Payment request failed to process");});
		    }else {
		    	violations.forEach(violation -> {
		    	    String message = violation.getPropertyPath() + ": " + violation.getMessage();
		    	    LOGGER.info("violation="+message);
		    	});
		    }
		}else {
			LOGGER.error("payment value is null");
		}
		
		return savedPayment;
	}
	
}
