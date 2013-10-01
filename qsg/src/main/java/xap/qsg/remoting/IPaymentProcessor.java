package xap.qsg.remoting;

import xap.tutorial.payment.model.Payment;

public interface IPaymentProcessor {

	Payment processPayment(Payment data);
}