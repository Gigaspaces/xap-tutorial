package xap.qsg.test.remoting;

import org.junit.Test;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import xap.qsg.query.service.IQueryService;
import xap.qsg.remoting.client.RemoteService;
import xap.tutorial.test.util.AbstractTutorialContextTests;
import xap.tutorial.test.util.SpaceUtility;

public class RemotingTest extends AbstractTutorialContextTests {

	@Autowired
	private SpaceUtility spaceUtility;

	@Autowired
	@Qualifier(IQueryService.SPACE)
	private GigaSpace space;

	@Autowired
	private RemoteService service;

	@Test
	public void remotingTest() {

		service.executePaymentService();
	}

}
