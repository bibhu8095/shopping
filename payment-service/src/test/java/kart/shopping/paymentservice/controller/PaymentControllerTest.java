package kart.shopping.paymentservice.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import kart.shopping.paymentservice.entity.Address;
import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.entity.PaymentType;
import kart.shopping.paymentservice.service.PaymentService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTest {
	@InjectMocks
	private PaymentController paymentController;
	@Mock
	private PaymentService paymentService;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenString_whencreatePaymentDetails_thenReturnResponse() {
		Address hyd = new Address();
		hyd.setAddressId(1);
		hyd.setLocation("Bangalore");
		hyd.setPincode("500000");
		Payment payment = new Payment();
		payment.setOrderId(1L);
		payment.setPrice(200);
		payment.setShippingAddress(hyd);
		payment.setPaymentType(PaymentType.UPI);
		Mockito.when(paymentService.savePaymentDetails(1L, 200, PaymentType.UPI, hyd)).thenReturn(payment);
		Payment actual = paymentController.createPayment(payment);
		Assert.assertEquals(payment, actual);
	}

}
