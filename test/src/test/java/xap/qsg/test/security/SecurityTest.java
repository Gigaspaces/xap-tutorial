package xap.qsg.test.security;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

import xap.tutorial.security.service.SecurityService;
import xap.tutorial.test.util.AbstractTutorialContextTests;

@Ignore
public class SecurityTest extends AbstractTutorialContextTests {

	@Autowired
	private SecurityService service;

	@Ignore
	public void createUser() {
		service.createUser();
	}

}
