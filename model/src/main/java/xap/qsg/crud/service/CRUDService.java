package xap.qsg.crud.service;

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
import com.gigaspaces.metadata.SpaceTypeDescriptor;
import com.gigaspaces.metadata.SpaceTypeDescriptorBuilder;
import com.gigaspaces.metadata.index.SpaceIndexType;
import com.gigaspaces.query.IdQuery;
import com.j_spaces.core.client.SQLQuery;
import com.j_spaces.core.client.UpdateModifiers;

@Service(ICRUDService.SERVICE)
public class CRUDService {

	@Autowired
	@Qualifier(ICRUDService.SPACE)
	private GigaSpace space;

	public void writeUser() {

		User user = new User();
		user.setId(new Long(1));
		user.setName("John Smith");
		user.setComment(new String[] {"This", "is","a","comment"});
		user.setBalance(new Double(10.5));
		user.setCreditLimit(new Double(1000.00));
		user.setStatus(EAccountStatus.BLOCKED);
		user.setStatus(EAccountStatus.ACTIVE);

		// Write the user to the space
		 space.write(user);
	}

	public void writeUsers() {
		User[] users = new User[2];

		users[0] = new User();
		users[0].setId(new Long(1));
		users[0].setName("John Dow");
		users[0].setStatus(EAccountStatus.ACTIVE);

		users[1] = new User();
		users[1].setId(new Long(2));
		users[1].setName("John Dow");
		users[1].setStatus(EAccountStatus.ACTIVE);

		space.writeMultiple(users);
	}

	public void writeOnlyWithLease() {
		User user = new User();
		user.setId(new Long(1));
		user.setName("John Smith");
		user.setStatus(EAccountStatus.ACTIVE);

		space.write(user, 0, 10000, WriteModifiers.WRITE_ONLY);
	}

	public void partialUpdate() {
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

	public SpaceDocument createDocumemt() {
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

		return document;
	}

	public void registerProductType() {
		// Create type Document descriptor:
		SpaceTypeDescriptor typeDescriptor = new SpaceTypeDescriptorBuilder(
				"Product").idProperty("CatalogNumber")
				.routingProperty("Category")
				.addPropertyIndex("Name", SpaceIndexType.BASIC)
				.addPropertyIndex("Price", SpaceIndexType.EXTENDED).create();
		// Register type:
		space.getTypeManager().registerTypeDescriptor(typeDescriptor);
	}

	public User takeUserById() {
		return space.takeById(User.class, 1L);
	}

	public User takeUserByTemplate() {
		User template = new User();
		template.setName("John Dow");
		return space.take(template);
	}

	public User takeUserBySQL() {
		SQLQuery<User> query = new SQLQuery<User>(User.class, "status = ?");
		query.setParameter(1, EAccountStatus.BLOCKED);
		return space.take(query);
	}

	public void clearUserByTemplate() {
		User template = new User();
		space.clear(template);
	}

	public void clearUserBySQL() {
		SQLQuery<User> query = new SQLQuery<User>(User.class, "name = ?");
		query.setParameter(1, "John Dow");
		space.clear(query);
	}

	public void clearAllObjectInSpace() {
		space.clear(null);
	}
}
