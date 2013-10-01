package xap.qsg.event;

import org.openspaces.core.GigaSpace;
import org.openspaces.events.notify.SimpleNotifyContainerConfigurer;
import org.openspaces.events.notify.SimpleNotifyEventListenerContainer;
import org.openspaces.events.polling.SimplePollingContainerConfigurer;
import org.openspaces.events.polling.SimplePollingEventListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(IEventService.SERVICE)
public class EventService {

	@Autowired
	@Qualifier(IEventService.SPACE)
	private GigaSpace space;

	public void registerNotifierListener() {
		SimpleNotifyEventListenerContainer notifyEventListenerContainer = new SimpleNotifyContainerConfigurer(
				space).eventListenerAnnotation(new PaymentListener())
				.notifyContainer();
		notifyEventListenerContainer.start();
	}

	public void registerNotifierListenerOnlyUpdate() {
		SimpleNotifyEventListenerContainer notifyEventListenerContainer = new SimpleNotifyContainerConfigurer(
				space).eventListenerAnnotation(new PaymentListener())
				.notifyUpdate(true).notifyWrite(false).notifyTake(false)
				.notifyContainer();
		notifyEventListenerContainer.start();
	}

	public void registerPollingListener() {
		SimplePollingEventListenerContainer pollingEventListenerContainer = new SimplePollingContainerConfigurer(
				space).eventListenerAnnotation(new AuditListener())
				.pollingContainer();
		pollingEventListenerContainer.start();
	}

}
