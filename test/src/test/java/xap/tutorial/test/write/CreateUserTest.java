package xap.tutorial.test.write;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xap.tutorial.test.util.AbstractTutorialContextTests;
import xap.tutorial.test.util.SpaceUtility;
import xap.tutorial.test.util.UserUtil;
import xap.tutorial.user.model.User;
import xap.tutorial.user.service.UserService;

public class CreateUserTest extends AbstractTutorialContextTests {

	@Autowired
	private UserService service;

	@Autowired
	private SpaceUtility spaceUtility;
	
	@Autowired
	private UserUtil userUtil;

	@Test
	public void createUser() {

		User user = userUtil.createUser();
		service.createUser(user);
	}

	@Test
	public void createUserWithLeaseTime() {

		User user = userUtil.createUser();
		service.createUserWithTimeToLive(user);
	}

	@After
	public void clear() {
		spaceUtility.clear(new User());
	}

}
