package kart.shopping.paymentservice.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kart.shopping.paymentservice.entity.Address;
import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.entity.PaymentType;
import kart.shopping.paymentservice.repository.PaymentRepository;
import kart.shopping.paymentservice.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static final Logger Logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public void savePayment(Payment payment) {

		Logger.info("savePayment start");

		try {
			if (payment != null) {
				paymentRepository.save(payment);
			}
			Logger.info(payment.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.info("Exception in save payment");
		}


	}

	@Override
	public Payment savePaymentDetails(Long orderId, double price, PaymentType paymentType, Address shippingAddress) {
		Logger.info("Save payment details");
		Payment payment=new Payment(orderId, price, paymentType, shippingAddress);
		try {
			if(payment!=null) {
				paymentRepository.save(payment);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return payment;
	}

}
