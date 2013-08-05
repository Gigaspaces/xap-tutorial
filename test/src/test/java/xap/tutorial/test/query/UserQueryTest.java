package xap.tutorial.test.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xap.tutorial.test.util.AbstractTutorialContextTests;
import xap.tutorial.test.util.UserUtil;
import xap.tutorial.user.model.User;
import xap.tutorial.user.service.UserQueryService;

public class UserQueryTest extends AbstractTutorialContextTests {

	@Autowired
	private UserQueryService service;

	@Autowired
	private UserUtil userUtil;
	
	
	@Before
	public void init()
	{
		userUtil.loadUsers();
	}
	
	
	@Test
	public void findAllUsers() {

		User[] users = service.findAllUsers();

		assertNotNull(users);
		assertEquals(1, users.length);
	}

	@Test
	public void findUserById() {

		User user = service.findUserById(new Integer(1));

		assertNotNull(user);
		assertEquals(new Integer(1), user.getId());
	}

}
