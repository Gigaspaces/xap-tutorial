package xap.qsg.service;

import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import xap.tutorial.account.model.EAccountStatus;
import xap.tutorial.user.model.User;

import com.gigaspaces.client.ChangeResult;
import com.gigaspaces.client.ChangeSet;
import com.gigaspaces.client.WriteModifiers;
import com.gigaspaces.document.DocumentProperties;
import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.query.IdQuery;
import com.j_spaces.core.LeaseContext;
import com.j_spaces.core.client.UpdateModifiers;

@Service(IQSGService.SERVICE)
public class QSGService {

	@Autowired
	@Qualifier(IQSGService.SPACE)
	private GigaSpace space;

	public User writeUser() {

		User user = new User();
		user.setId(new Long(1));
		user.setName("John Smith");
		user.setStatus(EAccountStatus.ACTIVE);

		// Write the user to the space
		LeaseContext<User> context = space.write(user);
		return context.getObject();
	}

	public void writeUsers() {
		User[] users = new User[2];

		users[0] = new User();
		users[0].setId(new Long(1));
		users[0].setName("John Dow");
		users[0].setStatus(EAccountStatus.ACTIVE);

		users[1] = new User();
		users[1].setId(new Long(1));
		users[1].setName("John Dow");
		users[1].setStatus(EAccountStatus.ACTIVE);

		space.writeMultiple(users);
	}

	public void writeOnlyWithLease() {
		User user = new User();
		user.setId(new Long(1));
		user.setName("John Smith");
		user.setStatus(EAccountStatus.ACTIVE);

		space.write(user, 0, 1000, WriteModifiers.WRITE_ONLY);
	}

	public void update() {
		User user = new User();
		user.setId(new Long(1));
		user.setName("John Dow");
		user.setStatus(EAccountStatus.ACTIVE);
		space.write(user);

		// Update the User
		user.setStatus(EAccountStatus.BLOCKED);
		space.write(user, UpdateModifiers.PARTIAL_UPDATE);
	}

	public void ChangeSet() {
		User user = new User();
		user.setId(new Long(1));
		user.setName("John Dow");
		user.setStatus(EAccountStatus.ACTIVE);
		space.write(user);

		IdQuery<User> idQuery = new IdQuery<User>(User.class, new Long(1));
		ChangeResult<User> changeResult = space.change(idQuery,
				new ChangeSet().set("status", EAccountStatus.BLOCKED));

		if (changeResult.getNumberOfChangedEntries() == 0) {
			System.out.println("Entry does not exist");
		}
	}

	public void createDocumemt() {
		DocumentProperties properties = new DocumentProperties()
				.setProperty("CatalogNumber", "av-9876")
				.setProperty("Category", "Aviation")
				.setProperty("Name", "Jet Propelled Pogo Stick")
				.setProperty("Price", 19.99f)
				.setProperty("Tags",
						new String[] { "New", "Cool", "Pogo", "Jet" })
				.setProperty(
						"Features",
						new DocumentProperties()
								.setProperty("Manufacturer", "Acme")
								.setProperty("RequiresAssembly", true)
								.setProperty("NumberOfParts", 42));

		SpaceDocument document = new SpaceDocument("Product", properties);

		space.write(document);
	}

 
}
