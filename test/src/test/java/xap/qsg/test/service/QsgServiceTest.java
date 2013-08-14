package xap.qsg.test.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import xap.qsg.service.IQSGService;
import xap.qsg.service.QSGService;
import xap.tutorial.test.util.AbstractTutorialContextTests;
import xap.tutorial.test.util.SpaceUtility;
import xap.tutorial.test.util.UserUtil;

public class QsgServiceTest extends AbstractTutorialContextTests {

	@Autowired
	private SpaceUtility spaceUtility;

	@Autowired
	private UserUtil userUtil;

	@Autowired
	@Qualifier(IQSGService.SPACE)
	private GigaSpace space;

	@Autowired
	private QSGService service;

	@Test
	public void writeUser() {
		service.writeUser();
	}

	@Test
	public void writeUsers() {
		service.writeUsers();
	}

	@Test
	public void writeOnlyWithLease() {
		service.writeOnlyWithLease();
	}

	@Test
	public void update() {
		service.update();
	}

	public void ChangeSet() {
		service.ChangeSet();
	}

	@Test
	public void createDocumemt() {
		service.createDocumemt();
	}

	@Before
	public void registerProductType() {

		service.registerProductType();
		//service.createDocumemt();

		// Create a user
		//userUtil.loadUsers();
	}

	@After
	public void clear() {
		spaceUtility.clear(null);
	}

}
