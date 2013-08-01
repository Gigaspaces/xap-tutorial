package xap.tutorial.user.service;

import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import xap.tutorial.user.model.User;


@Service(IUserService.QUERY_SERVICE)
public class UserQueryService {

	@Autowired
	@Qualifier(IUserService.SPACE)
	private GigaSpace space;

	// Return a collection of Users
	public User[] findAllUsers() {

		User template = new User();
		return space.readMultiple(template);
	}

	// Find User by id
	public User findUserById(Integer id) {

		return space.readById(User.class, id);
	}
}
