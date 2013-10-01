package xap.qsg.pu.service;

import java.util.Date;

import org.openspaces.core.GigaSpace;
import org.openspaces.events.polling.SimplePollingContainerConfigurer;
import org.openspaces.events.polling.SimplePollingEventListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import xap.qsg.pu.ClientListener;
import xap.tutorial.payment.model.ETransactionStatus;
import xap.tutorial.payment.model.Payment;

@Service(IPUService.SERVICE)
public class PUService {

	@Autowired
	@Qualifier(IPUService.SPACE)
	private GigaSpace space;

	public void registerPollingListener() {
		Payment payment = new Payment();
		payment.setStatus(ETransactionStatus.PROCESSED);

		SimplePollingEventListenerContainer pollingEventListenerContainer = new SimplePollingContainerConfigurer(
				space).eventListenerAnnotation(new ClientListener())
				.pollingContainer();
		pollingEventListenerContainer.start();
	}

	public void postPayment() {
		// Register the an event handler on the psace
		this.registerPollingListener();

		// Create a payment
		Payment payment = new Payment();
		payment.setCreatedDate(new Date(System.currentTimeMillis()));
		payment.setMerchantId(new Long(1));
		payment.setPaymentAmount(new Double(120.70));
		payment.setStatus(ETransactionStatus.NEW);

		// write the payment into the spaceO
		space.write(payment);
	}
}
