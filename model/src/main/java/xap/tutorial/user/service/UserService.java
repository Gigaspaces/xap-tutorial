package xap.tutorial.user.service;

import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import xap.tutorial.user.model.User;

import com.gigaspaces.client.WriteModifiers;

@Service(IUserService.SERVICE)
public class UserService {

	@Autowired
	@Qualifier(IUserService.SPACE)
	private GigaSpace space;

	public void createOrUpdateUser(User user) {
		space.write(user);
	}

	public void createUser(User user) {
		space.write(user, WriteModifiers.WRITE_ONLY);
	}

	public void createUserWithTimeToLive(User user) {
		space.write(user, 100000);
	}

	public void updateUser(User user) {
		space.write(user, WriteModifiers.UPDATE_ONLY);
	}

}
