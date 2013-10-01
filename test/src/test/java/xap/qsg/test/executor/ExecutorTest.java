package xap.qsg.test.executor;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import xap.qsg.executor.ExecutorService;
import xap.qsg.query.service.IQueryService;
import xap.tutorial.test.util.AbstractTutorialContextTests;
import xap.tutorial.test.util.SpaceUtility;

public class ExecutorTest extends AbstractTutorialContextTests {

	@Autowired
	private SpaceUtility spaceUtility;

	@Autowired
	@Qualifier(IQueryService.SPACE)
	private GigaSpace space;

	@Autowired
	private ExecutorService service;

	@Test
	public void executeTask() throws InterruptedException, ExecutionException {
		service.executeTask();

	}

	@Test
	public void executeTaskWithRouting() throws InterruptedException,
			ExecutionException {
		service.executeTaskWithRouting();

	}

	@Test
	public void executeTaskWithRoutingAnnoation() throws InterruptedException,
			ExecutionException {
		service.executeTaskWithRoutingPOJO();

	}

	@Test
	public void executeTaskRoutingAnnoation() throws InterruptedException,
			ExecutionException {
		service.executeTaskRoutingAnnoation();

	}

	@Test
	public void executeDistgributedTask() throws InterruptedException,
			ExecutionException {
		service.executeDistributedTask();

	}
	
	
	
	@Test
	public void asyncListenerTest() {
		try {
			service.executeAsyncTask();
			
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
