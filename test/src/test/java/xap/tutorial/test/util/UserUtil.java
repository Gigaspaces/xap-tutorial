package xap.tutorial.test.util;

import java.util.ArrayList;
import java.util.List;

import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import xap.qsg.query.service.IQueryService;
import xap.tutorial.account.model.EAccountStatus;
import xap.tutorial.contact.model.Address;
import xap.tutorial.contact.model.EContactType;
import xap.tutorial.contact.model.ECountry;
import xap.tutorial.user.model.Group;
import xap.tutorial.user.model.User;

@Service
public class UserUtil {

	@Autowired
	@Qualifier(IQueryService.SPACE)
	private GigaSpace gigaSpace;

	public void loadUsers() {

		User u = new User();
		u.setId(new Long(1));
		u.setBalance(120.90d);
		u.setCreditLimit(1500.00d);
		u.setName("John Dow");
		u.setStatus(EAccountStatus.ACTIVE);

		Address a = new Address();
		a.setCity("NYC");
		a.setCountry(ECountry.USA);
		a.setState("NY");
		a.setStreet("100th East");
		a.setZipCode(new Integer(12345));
		u.setAddress(a);
		u.addContact(EContactType.HOME, "770-123-5555");

		List<Integer> ratings = new ArrayList<Integer>();
		ratings.add(new Integer(1));
		ratings.add(new Integer(4));
		u.setRatings(ratings);

		List<Group> groups = new ArrayList<Group>();
		Group g = new Group();
		g.setId(1L);
		g.setName("Group 1");
		groups.add(g);
		u.setGroups(groups);

		u.setComment(new String[] { "existing", "customer" });
		gigaSpace.write(u);

		u = new User();
		u.setId(new Long(2));
		u.setBalance(120.90d);
		u.setCreditLimit(500.00d);
		u.setName("Customer 2");
		u.setStatus(EAccountStatus.INACTIVE);

		a = new Address();
		a.setCity("NYC");
		a.setCountry(ECountry.USA);
		a.setState("NY");
		a.setStreet("100th East");
		a.setZipCode(new Integer(10017));
		u.setAddress(a);

		ratings = new ArrayList<Integer>();
		ratings.add(new Integer(1));
		ratings.add(new Integer(3));
		u.setRatings(ratings);

		gigaSpace.write(u);

		u = new User();
		u.setId(new Long(3));
		u.setBalance(120.90d);
		u.setCreditLimit(500.00d);
		u.setName("Customer 2");
		u.setStatus(EAccountStatus.BLOCKED);

		a = new Address();
		a.setCity("NYC");
		a.setCountry(ECountry.USA);
		a.setState("NY");
		a.setStreet("100th East");
		a.setZipCode(new Integer(12345));
		u.setAddress(a);

		gigaSpace.write(u);
	}
}
