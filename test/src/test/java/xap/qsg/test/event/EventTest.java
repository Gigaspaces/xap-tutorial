package xap.qsg.test.event;

import org.junit.Test;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import xap.qsg.event.IEventService;
import xap.tutorial.payment.model.ETransactionStatus;
import xap.tutorial.payment.model.Payment;
import xap.tutorial.test.util.AbstractTutorialContextTests;
import xap.tutorial.test.util.SpaceUtility;

public class EventTest extends AbstractTutorialContextTests {

	@Autowired
	private SpaceUtility spaceUtility;

	@Autowired
	@Qualifier(IEventService.SPACE)
	private GigaSpace space;

	@Test
	public void notifyTest() {

		// Enable this if you do not want to use Annotations
		// service.registerNotifierListener();

		Payment payment = new Payment();
		payment.setStatus(ETransactionStatus.CANCELLED);

		space.write(payment);
	}

	@Test
	public void pollingTest() {

		// Enable this if you do not want to use Annotations
		// service.registerPollingListener();

		Payment payment = new Payment();
		payment.setStatus(ETransactionStatus.AUDITED);

		space.write(payment);
	}

}
