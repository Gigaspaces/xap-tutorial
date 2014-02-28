package xap.tutorial.security.service;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SecurityConfig;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.springframework.stereotype.Service;

import com.gigaspaces.security.SecurityFactory;
import com.gigaspaces.security.SecurityManager;
import com.gigaspaces.security.authorities.RoleAuthority;
import com.gigaspaces.security.authorities.SpaceAuthority;
import com.gigaspaces.security.authorities.SpaceAuthority.SpacePrivilege;
import com.gigaspaces.security.directory.DirectoryManager;
import com.gigaspaces.security.directory.Role;
import com.gigaspaces.security.directory.RoleManager;
import com.gigaspaces.security.directory.User;
import com.gigaspaces.security.directory.UserDetails;
import com.gigaspaces.security.directory.UserManager;

@Service
public class SecurityService {

	private UserManager userManager = null;

	private RoleManager roleManager = null;

	public void createUser() {

 
		// Create the User
		User user = new User("student", "student123", new RoleAuthority(
				"training"));

		try {
			UserDetails s = userManager.getUser("student");
			System.out.println(s);

			userManager.deleteUser("student");
		} catch (Exception ex) {

		}
		userManager.createUser(user);
	}

	public Role createRole() {

		Role role = new Role("training",
				new SpaceAuthority(SpacePrivilege.READ), new SpaceAuthority(
						SpacePrivilege.WRITE), new SpaceAuthority(
						SpacePrivilege.TAKE));
		roleManager.createRole(role);
		return role;
	}

	public void createSecureSpace() {
		UrlSpaceConfigurer urlSpaceConfigurer = new UrlSpaceConfigurer(
				"/./xapTutorialSpace").secured(true);
		@SuppressWarnings("unused")
		GigaSpace gigaSpace = new GigaSpaceConfigurer(urlSpaceConfigurer)
				.gigaSpace();
	}

	public void connectToSecureSpace() {
		SecurityConfig config = new SecurityConfig();
		config.setPassword("student2");
		config.setUsername("student2");

		UrlSpaceConfigurer urlSpaceConfigurer = new UrlSpaceConfigurer(
				"jini://*/*/xapTutorialSpace").securityConfig(config);

		@SuppressWarnings("unused")
		GigaSpace gigaSpace = new GigaSpaceConfigurer(urlSpaceConfigurer)
				.gigaSpace();
	}

	@PostConstruct
	public void init() {
		Properties securityProperties = new Properties();
		SecurityManager securityManager = SecurityFactory
				.createSecurityManager(securityProperties);

		DirectoryManager directoryManager = securityManager
				.createDirectoryManager(new User("admin", "admin"));

		roleManager = directoryManager.getRoleManager();
		userManager = directoryManager.getUserManager();
	}
}
