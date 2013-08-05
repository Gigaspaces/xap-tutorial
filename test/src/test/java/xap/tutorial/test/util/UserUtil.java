package xap.tutorial.test.util;

import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import xap.tutorial.account.model.EAccountStatus;
import xap.tutorial.address.model.Address;
import xap.tutorial.address.model.ECountry;
import xap.tutorial.user.model.User;
import xap.tutorial.user.service.IUserService;


@Service
public class UserUtil {

	@Autowired
	@Qualifier(IUserService.SPACE)
	private GigaSpace gigaSpace;

	public void loadUsers() {

		gigaSpace.write(this.createUser());

	}

	public User createUser() {

		User u = new User();
		u.setId(new Integer(1));
		u.setBalance(120.90d);
		u.setCreditLimit(500.00d);
		u.setName("Customer 1");
		u.setStatus(EAccountStatus.ACTIVE);

		Address a = new Address();
		a.setCity("NYC");
		a.setCountry(ECountry.USA);
		a.setState("NY");
		a.setStreet("100th East");
		a.setZipCode(new Integer(10017));
		u.setAddress( a);

		return u;
	}
}
