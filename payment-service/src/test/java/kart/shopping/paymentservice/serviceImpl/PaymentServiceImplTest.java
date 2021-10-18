package kart.shopping.paymentservice.serviceImpl;

import static org.mockito.Mockito.doThrow;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;

import kart.shopping.paymentservice.entity.Address;
import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.entity.PaymentType;
import kart.shopping.paymentservice.repository.PaymentRepository;
import kart.shopping.paymentservice.service.PaymentService;
import kart.shopping.paymentservice.util.EntityUtil;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {

	@InjectMocks
	private PaymentService paymentService = new PaymentServiceImpl();

	@Mock
	private PaymentRepository paymentRepository;

	

	@Test
	public void givenString_whensavePayment_thenReturnResponse() {
		/*
		 * Payment inputPayment = new Payment(); Address addr=new Address();
		 * inputPayment.setOrderId(1L); inputPayment.setPrice(200);
		 * inputPayment.setShippingAddress(addr);
		 * inputPayment.setPaymentType(PaymentType.UPI);
		 */
		Payment inputPayment = EntityUtil.getPayment();
		
		Mockito.when(paymentRepository.save(inputPayment)).thenReturn(inputPayment);
		Payment payment=paymentService.savePayment(inputPayment).get();
		Assert.assertNotNull(payment);
		Assert.assertEquals(payment,inputPayment);
	}

	@Test
	public void givenString_whensavePaymentException_thenReturnResponse() {
		Optional<Payment> payment=paymentService.savePayment(null);
		Assert.assertTrue(payment.isEmpty());
	}

	@Test
	public void givenString_whensavePaymentDetails_thenReturnResponse() {
		Payment inputPayment = EntityUtil.getPayment();
		
		Address hyd = new Address();
		hyd.setAddressId(1);
		hyd.setLocation("Bangalore");
		hyd.setPincode("500000");
		/*
		 * Payment payment = new Payment(); payment.setOrderId(1L);
		 * payment.setPrice(200); payment.setShippingAddress(hyd);
		 * payment.setPaymentType(PaymentType.UPI);
		 */
		Mockito.when(paymentRepository.save(inputPayment)).thenReturn(inputPayment);
		Optional<Payment> actual = paymentService.savePaymentDetails(1L, 200, PaymentType.UPI, hyd);
		Assert.assertNotNull(actual);
		//Assert.assertEquals(payment,actual);
	}
	
	@Test
	public void givenString_whensavePaymentDetailsException_thenReturnResponse() {
		
		Mockito.when(paymentRepository.save(Mockito.any())).thenThrow(new DataRetrievalFailureException("Test"));
		Optional<Payment> actual = paymentService.savePaymentDetails(1L, 200, PaymentType.UPI, new Address());
		Assert.assertTrue(actual.isEmpty());
	}

}
