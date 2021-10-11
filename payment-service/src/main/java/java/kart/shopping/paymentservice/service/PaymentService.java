package kart.shopping.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kart.shopping.paymentservice.entities.Payment;
import kart.shopping.paymentservice.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public void savePayment(Payment payment) {
		
		System.out.println("savePayment start");
		
		try {
			if(payment!=null) {
				paymentRepository.save(payment);
			}
			System.out.println(payment.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in save payment" );
		}
		
		System.out.println("savePayment start");
		
	}
	
	
	
}
