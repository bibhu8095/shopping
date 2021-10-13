package kart.shopping.paymentservice.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
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
import kart.shopping.paymentservice.repository.PaymentRepository;
import kart.shopping.paymentservice.service.PaymentService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {

	@InjectMocks
	private PaymentService paymentService=new PaymentServiceImpl();
	
	@Mock
	private PaymentRepository paymentRepository;
	
	@SuppressWarnings("deprecation")
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void givenString_whensavePaymentDetails_thenReturnResponse() {
		Address hyd = new Address();
		Payment payment=new Payment();
		payment.setId(1);
		payment.setPrice(200);
		payment.setShippingAddress(hyd);
		payment.setPaymentType(PaymentType.UPI);
		
		Mockito.when(paymentRepository.save(payment)).thenReturn(payment);
		Payment actual=paymentService.savePaymentDetails(1L, 200, PaymentType.UPI,hyd);
		assertEquals(actual, payment);
	}
}
