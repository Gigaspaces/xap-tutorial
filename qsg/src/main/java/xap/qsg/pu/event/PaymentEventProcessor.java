package xap.qsg.pu.event;

import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.NotifyType;
import org.openspaces.events.polling.Polling;

import xap.tutorial.payment.model.ETransactionStatus;
import xap.tutorial.payment.model.Payment;

@EventDriven
@Polling
@NotifyType(write = true)
public class PaymentEventProcessor {
	
	// Define the event we are interested in
	@EventTemplate
	Payment unprocessedData() {
		Payment template = new Payment();
		template.setStatus(ETransactionStatus.NEW);
		return template;
	}

	@SpaceDataEvent
	public Payment eventListener(Payment event) {
		System.out.println("Payment received; processing .....");

		// set the status on the event and write it back into the space
		event.setStatus(ETransactionStatus.PROCESSED);
		return event;
	}
}