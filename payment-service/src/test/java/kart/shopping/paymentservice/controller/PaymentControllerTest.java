package kart.shopping.paymentservice.controller;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import kart.shopping.paymentservice.entity.Address;
import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.entity.PaymentType;
import kart.shopping.paymentservice.service.PaymentService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentControllerTest.class);
	
	@InjectMocks
	private PaymentController paymentController;
	@Mock
	private PaymentService paymentService;

	@Test
	public void testCreatePaymentDetails() {
		//Payment inputPayment = EntityUtil.getPayment();
		LOGGER.info("testCreatePaymentDetails start");
		Address hyd = new Address();
		hyd.setAddressId(1);
		hyd.setLocation("Bangalore");
		hyd.setPincode("500000");
		Payment payment = new Payment();
		payment.setOrderId(1L);
		payment.setPrice(200);
		payment.setShippingAddress(hyd);
		payment.setPaymentType(PaymentType.UPI);

		Mockito.when(paymentService.savePaymentDetails(1L, 200, PaymentType.UPI, hyd))
				.thenReturn(Optional.ofNullable(payment));
		Payment actual = paymentController.createPayment(payment);
		Assert.assertEquals(payment, actual);
		LOGGER.info("testCreatePaymentDetails end");
	}

}
