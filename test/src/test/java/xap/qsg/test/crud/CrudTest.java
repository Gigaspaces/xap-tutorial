package xap.qsg.test.crud;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import xap.qsg.crud.service.CRUDService;
import xap.qsg.query.service.IQueryService;
import xap.tutorial.account.model.EAccountStatus;
import xap.tutorial.test.util.AbstractTutorialContextTests;
import xap.tutorial.test.util.SpaceUtility;
import xap.tutorial.test.util.UserUtil;
import xap.tutorial.user.model.User;

import com.gigaspaces.document.SpaceDocument;

public class CrudTest extends AbstractTutorialContextTests {

	@Autowired
	private SpaceUtility spaceUtility;

	@Autowired
	private UserUtil userUtil;

	@Autowired
	@Qualifier(IQueryService.SPACE)
	private GigaSpace space;

	@Autowired
	private CRUDService service;

	@Test
	public void writeUser() {

		service.writeUser();
		int count = space.count(new User());
		Assert.assertEquals(1, count);
	}

	@Test
	public void writeUsers() {
		service.writeUsers();
		int count = space.count(new User());
		Assert.assertEquals(2, count);
	}

	@Test
	public void writeOnlyWithLease() {
		service.writeOnlyWithLease();
		int count = space.count(new User());
		Assert.assertEquals(1, count);
	}

	@Test
	public void partialUpdate() {
		service.partialUpdate();

		User u = space.read(new User());
		Assert.assertEquals(EAccountStatus.BLOCKED, u.getStatus());
	}

	@Test
	public void ChangeSet() {
		service.ChangeSet();
		User u = space.read(new User());
		Assert.assertEquals(EAccountStatus.BLOCKED, u.getStatus());
	}

	@Test
	public void createDocumemt() {
		service.createDocumemt();

		SpaceDocument doc = space.read(new SpaceDocument("Product"));
		Assert.assertNotNull(doc);
	}

	@Test
	public void takeUserById() {

		userUtil.loadUsers();
		User user = service.takeUserById();
		Assert.assertNotNull(user);
		Assert.assertEquals(new Long(1), user.getId());
	}

	@Test
	public void takeUserByTemplate() {

		userUtil.loadUsers();
		User user = service.takeUserByTemplate();

		Assert.assertEquals("John Dow", user.getName());
	}

	@Test
	public void takeUserBySQL() {

		userUtil.loadUsers();
		User user = service.takeUserBySQL();
		Assert.assertEquals(EAccountStatus.BLOCKED, user.getStatus());
	}

	@Test
	public void clearUserByTemplate() {

		userUtil.loadUsers();
		service.clearUserByTemplate();

		int count = space.count(new User());
		Assert.assertEquals(0, count);
	}

	@Test
	public void clearUserBySQL() {

		userUtil.loadUsers();
		service.clearUserBySQL();

		int count = space.count(new User());
		Assert.assertEquals(2, count);

	}

	@Test
	public void clearAll() {

		userUtil.loadUsers();
		service.clearAllObjectInSpace();

		int count = space.count(new User());
		Assert.assertEquals(0, count);
	}

	@Before
	public void registerProductType() {
		service.registerProductType();
	}

	@After
	public void clear() {
		spaceUtility.clear(null);
	}

}
