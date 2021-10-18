package kart.shopping.paymentservice.rabbitmq;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import kart.shopping.paymentservice.entity.Address;
import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.entity.PaymentType;
import kart.shopping.paymentservice.service.PaymentService;

@SpringBootTest
public class PaymentConsumerTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumerTest.class);
	
	@InjectMocks
	private PaymentConsumer paymentConsumer;
	
	@Mock
	private PaymentService paymentService;
	
	@Test
	public void testConsumePaymentMessage() {
		LOGGER.info("testConsumePaymentMessage start");
		Address shippingAddess = new Address(11,"11A Street, Rome", "001122");
		Payment inputPayment = new Payment(11L, 12.5, PaymentType.DebitCards, shippingAddess);
		inputPayment.setId(1);
		shippingAddess.setId(1);
		Mockito.when(paymentService.savePayment(inputPayment)).thenReturn(Optional.ofNullable(inputPayment));
		Payment savedPayment = paymentConsumer.consumePaymentMessage(inputPayment).orElse(null);
		Assert.assertNotNull(savedPayment);
		Assert.assertEquals(inputPayment, savedPayment);
		LOGGER.info("testConsumePaymentMessage end");
	}
	
	@Test
	public void testConsumePaymentMessageNullValue() {
		LOGGER.info("testConsumePaymentMessageNullValue start");
		Mockito.when(paymentService.savePayment(null)).thenReturn(Optional.empty());
		Payment savedPayment = paymentConsumer.consumePaymentMessage(null).orElse(null);
		Assert.assertNull(savedPayment);
		LOGGER.info("testConsumePaymentMessageNullValue end");
	}

}


