package xap.qsg.test.security;

import java.util.Properties;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xap.tutorial.security.service.SecurityService;
import xap.tutorial.test.util.AbstractTutorialContextTests;

import com.gigaspaces.security.SecurityFactory;
import com.gigaspaces.security.SecurityManager;
import com.gigaspaces.security.directory.DirectoryManager;
import com.gigaspaces.security.directory.RoleManager;
import com.gigaspaces.security.directory.User;
import com.gigaspaces.security.directory.UserDetails;
import com.gigaspaces.security.directory.UserManager;

 
public class SecurityTest extends AbstractTutorialContextTests {

	@Autowired
	private SecurityService service;

	@Test
	public void createUser() {
		service.createUser();
	}

//	@Before
	public void clearUsers() {

		Properties securityProperties = new Properties();
		SecurityManager securityManager = SecurityFactory
				.createSecurityManager(securityProperties);

		DirectoryManager directoryManager = securityManager
				.createDirectoryManager(new User("admin", "admin"));

		UserManager userManager = directoryManager.getUserManager();
		
	//	UserDetails s = userManager.getUser("student");
	//	System.out.println(s);
	//	userManager.deleteUser("student");
	//	s = userManager.getUser("student");
	//	System.out.println(s);
		RoleManager roleManager = directoryManager.getRoleManager();
	//	roleManager.deleteRole("training");

	//	try {
	//		System.out.println(roleManager.getRole("training"));
	//	} catch (Exception ex) {
	//		ex.printStackTrace();
	//	}
	}
}
