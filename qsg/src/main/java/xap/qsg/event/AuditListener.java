package xap.qsg.event;

import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.NotifyType;
import org.openspaces.events.polling.Polling;

import xap.tutorial.payment.model.ETransactionStatus;
import xap.tutorial.payment.model.Payment;

@EventDriven
@Polling
@NotifyType(write = true, update = true)
public class AuditListener {

	@EventTemplate
	Payment unprocessedData() {
		Payment template = new Payment();
		template.setStatus(ETransactionStatus.AUDITED);
		return template;
	}

	@SpaceDataEvent
	public Payment eventListener(Payment event) {
		// process Payment
		System.out.println("Polling Received a payment:");		
		return null;
	}
}