package xap.qsg.pu;

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
public class ClientListener {

	// Define the event we are interested in
	@EventTemplate
	Payment unprocessedData() {
		Payment template = new Payment();
		template.setStatus(ETransactionStatus.PROCESSED);
		return template;
	}

	@SpaceDataEvent
	public void eventListener(Payment event) {
		System.out
				.println("Payment received with status :" + event.getStatus());

	}
}