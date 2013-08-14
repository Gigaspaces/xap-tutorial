package xap.qsg.test.query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import xap.qsg.service.IQSGService;
import xap.qsg.service.QSGQueryService;
import xap.qsg.service.QSGService;
import xap.tutorial.test.util.AbstractTutorialContextTests;
import xap.tutorial.test.util.SpaceUtility;
import xap.tutorial.test.util.UserUtil;
import xap.tutorial.user.model.User;

import com.gigaspaces.document.SpaceDocument;

public class QsgQueryTest extends AbstractTutorialContextTests {

	@Autowired
	private SpaceUtility spaceUtility;

	@Autowired
	private UserUtil userUtil;

	@Autowired
	@Qualifier(IQSGService.SPACE)
	private GigaSpace space;

	@Autowired
	private QSGQueryService queryService;

	@Autowired
	private QSGService service;

	@Test
	public void findUserById() {
		User user = queryService.findUserById();
		Assert.assertNotNull(user);
	}

	@Test
	public void findUsersByIds() {
		User[] users = queryService.findUsersByIds();

		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 3);
	}

	@Test
	public void findUserByTemplate() {
		User user = queryService.findUserByTemplate();
		Assert.assertNotNull(user);
	}

	@Test
	public void findUsersByTemplate() {
		User[] users = queryService.findUsersByTemplate();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 1);
	}

	@Test
	public void sqlFindUsersByName() {
		User[] users = queryService.sqlFindUsersByName();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 1);
	}

	@Test
	public void sqlFindUsersByNameAndCreditLimit() {
		User[] users = queryService.sqlFindUsersByNameAndCreditLimit();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 1);
	}

	@Test
	public void sqlFindUsersByNameAndIds() {
		User[] users = queryService.sqlFindUsersByNameAndIds();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 1);
	}

	@Test
	public void sqlParameterFindUsersByName() {
		User[] users = queryService.sqlParameterFindUsersByName();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 1);
	}

	@Test
	public void sqlParameterFindUsersByNameAndCreditLimit() {
		User[] users = queryService.sqlParameterFindUsersByNameAndCreditLimit();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 1);
	}

	@Test
	public void sqlFindUsersByZipCode() {
		User[] users = queryService.sqlFindUsersByZipCode();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 2);
	}

	@Test
	public void sqlFindUserByPhoneNumber() {
		User user = queryService.sqlFindUserByPhoneNumber();
		Assert.assertNotNull(user);

	}

	@Test
	public void findUsersByNameAndProjection() {
		User[] users = queryService.findUsersByNameAndProjection();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 1);
	}

	@Test
	public void findUsersByrating() {
		User[] users = queryService.findUsersByrating();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 2);
	}

	@Test
	public void findUsersByGroup() {
		User[] users = queryService.findUsersByGroup();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 1);
	}

	@Test
	public void findUsersByComment() {
		User[] users = queryService.findUsersByComment();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 1);
	}

	@Test
	public void findUsersBySQLExpression() {
		User[] users = queryService.findUsersBySQLExpression();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 1);
	}

	@Test
	public void findUsersByRegularExpression() {
		User[] users = queryService.findUsersByRegularExpression();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 1);
	}

	@Test
	public void findUsersByEnum() {
		User[] users = queryService.findUsersByEnum();
		Assert.assertNotNull(users);
		Assert.assertEquals(users.length, 1);
	}

	@Test
	public void readProductById() {
		SpaceDocument doc = queryService.readProductById();
		Assert.assertNotNull(doc);

	}

	@Test
	public void readProductByTemplate() {
		SpaceDocument doc = queryService.readProductByTemplate();
		Assert.assertNotNull(doc);
	}

	@Test
	public void readProductsBySQL() {
		SpaceDocument[] docs = queryService.readProductsBySQL();
		Assert.assertNotNull(docs);
		Assert.assertEquals(docs.length, 1);
	}

	@Before
	public void registerProductType() {

		service.registerProductType();
		service.createDocumemt();

		// Create a user
		userUtil.loadUsers();
	}

	@After
	public void clear() {
		spaceUtility.clear(null);
	}

}
