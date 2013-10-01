package xap.qsg.remoting.server;

import org.openspaces.remoting.RemotingService;

import xap.qsg.remoting.IPaymentProcessor;
import xap.tutorial.payment.model.Payment;

@RemotingService
public class PaymentProcessor implements IPaymentProcessor {

	public Payment processPayment(Payment payment) {
		System.out.println("Processing payment ");
		return payment;
	}
}
