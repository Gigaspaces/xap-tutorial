package xap.qsg.remoting.client;

import org.openspaces.core.GigaSpace;
import org.openspaces.remoting.ExecutorProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import xap.qsg.remoting.IPaymentProcessor;
import xap.qsg.remoting.IRemotingService;
import xap.tutorial.payment.model.Payment;

@Service(IRemotingService.SERVICE)
public class RemoteService {

	@Autowired
	@Qualifier(IRemotingService.SPACE)
	private GigaSpace space;

	@ExecutorProxy
	private IPaymentProcessor dataProcessor;

	public void executePaymentService() {

		Payment payment = dataProcessor.processPayment(new Payment());
		System.out.println(payment);
	}

}
