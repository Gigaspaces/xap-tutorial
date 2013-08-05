package xap.tutorial.test.write;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openspaces.core.EntryNotInSpaceException;
import org.springframework.beans.factory.annotation.Autowired;

import xap.tutorial.test.util.AbstractTutorialContextTests;
import xap.tutorial.test.util.SpaceUtility;
import xap.tutorial.test.util.UserUtil;
import xap.tutorial.user.model.User;
import xap.tutorial.user.service.UserService;

public class UpdateUserTest extends AbstractTutorialContextTests {

	@Autowired
	private UserService service;

	@Autowired
	private SpaceUtility spaceUtility;

	@Autowired
	private UserUtil userUtil;

	@Test
	public void updateUserDoesNotExist() {

		User user = new User();
		user.setId(new Integer(1));

		try {
			service.updateUser(user);
			Assert.fail();

		} catch (EntryNotInSpaceException ex) {

		}
	}

	@After
	public void clear() {
		spaceUtility.clear(new User());
	}

}
