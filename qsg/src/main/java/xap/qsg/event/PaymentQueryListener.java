package xap.qsg.event;

import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.Notify;

import xap.tutorial.payment.model.ETransactionStatus;
import xap.tutorial.payment.model.Payment;

import com.j_spaces.core.client.SQLQuery;

@EventDriven
@Notify
public class PaymentQueryListener {

	@EventTemplate
	SQLQuery<Payment> unprocessedData() {
		SQLQuery<Payment> template = new SQLQuery<Payment>(Payment.class,
				"status = ?");
		template.setParameter(1, ETransactionStatus.CANCELLED);
		return template;
	}

	@SpaceDataEvent
	public Payment eventListener(Payment event) {
		return null;
	}
}