package kart.shopping.paymentservice.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kart.shopping.paymentservice.entity.Address;
import kart.shopping.paymentservice.entity.Payment;
import kart.shopping.paymentservice.repository.PaymentRepository;
import kart.shopping.paymentservice.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public void savePayment(Payment payment) {

		System.out.println("savePayment start");

		try {
			if (payment != null) {
				paymentRepository.save(payment);
			}
			System.out.println(payment.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in save payment");
		}

		System.out.println("savePayment start");

	}

	@Override
	public Payment savePaymentDetails(int orderId, double price, String paymentType, Address shippingAddress) {
		Payment payment = new Payment(orderId, orderId, price, paymentType, shippingAddress);
		return paymentRepository.save(payment);
	}

}
