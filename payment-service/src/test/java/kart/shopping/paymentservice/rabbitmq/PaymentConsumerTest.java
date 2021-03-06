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

import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.service.PaymentService;
import kart.shopping.paymentservice.util.EntityUtil;

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
		Payment inputPayment = EntityUtil.getPayment();
		Mockito.when(paymentService.savePayment(inputPayment)).thenReturn(Optional.ofNullable(inputPayment));
		Payment savedPayment = paymentConsumer.consumePaymentMessage(inputPayment).orElse(null);
		Assert.assertNotNull(savedPayment);
		Assert.assertEquals(inputPayment, savedPayment);
		LOGGER.info("testConsumePaymentMessage end");
	}
	
	@Test
	public void testConsumePaymentMessageNullValue() {
		LOGGER.info("testConsumePaymentMessageNullValue start");
		Payment savedPayment = paymentConsumer.consumePaymentMessage(null).orElse(null);
		Assert.assertNull(savedPayment);
		LOGGER.info("testConsumePaymentMessageNullValue end");
	}

	@Test
	public void testConsumePaymentMessageInvalidData() {
		LOGGER.info("testConsumePaymentMessageInvalidData start");
		Payment inputPayment = EntityUtil.getPayment();
		inputPayment.setOrderId(null);
		Payment savedPayment = paymentConsumer.consumePaymentMessage(inputPayment).orElse(null);
		Assert.assertNull(savedPayment);
		LOGGER.info("testConsumePaymentMessageInvalidData end");
	}
}


