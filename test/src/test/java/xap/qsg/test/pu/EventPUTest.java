package xap.qsg.test.pu;

import java.util.Date;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.openspaces.events.polling.SimplePollingContainerConfigurer;
import org.openspaces.events.polling.SimplePollingEventListenerContainer;

import xap.qsg.pu.ClientListener;
import xap.tutorial.payment.model.ETransactionStatus;
import xap.tutorial.payment.model.Payment;

public class EventPUTest {

	private GigaSpace space;

	private String url = "jini://*/*/eventSpace";

	public static void main(String[] args) throws InterruptedException {

		EventPUTest test = new EventPUTest();

		test.registerPollingListener();
		test.postPayment();
		Thread.sleep(10000);
	}

	public void registerPollingListener() {

		SimplePollingEventListenerContainer pollingEventListenerContainer = new SimplePollingContainerConfigurer(
				space).eventListenerAnnotation(new ClientListener())
				.pollingContainer();
		pollingEventListenerContainer.start();
	}

	public void postPayment() {
		// Create a payment
		Payment payment = new Payment();
		payment.setCreatedDate(new Date(System.currentTimeMillis()));
		payment.setMerchantId(new Long(1));
		payment.setPaymentAmount(new Double(120.70));
		payment.setStatus(ETransactionStatus.NEW);

		// write the payment into the spaceO
		space.write(payment);
	}

	public EventPUTest() {
		space = new GigaSpaceConfigurer(new UrlSpaceConfigurer(url))
				.gigaSpace();
	}
}
