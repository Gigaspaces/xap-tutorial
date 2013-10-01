package xap.qsg.event;

import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.TransactionalEvent;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.Notify;

import xap.tutorial.payment.model.ETransactionStatus;
import xap.tutorial.payment.model.Payment;

@EventDriven
@Notify
@TransactionalEvent
public class PaymentListener {
	@EventTemplate
	Payment unprocessedData() {
		Payment template = new Payment();
		template.setStatus(ETransactionStatus.CANCELLED);
		return template;
	}

	@SpaceDataEvent
	public Payment eventListener(Payment event) {
		// process Payment
		System.out.println("Notifier Received a payment");
		return null;
	}
}