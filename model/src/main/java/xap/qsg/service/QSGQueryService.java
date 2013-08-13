package xap.qsg.service;

import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import xap.tutorial.account.model.EAccountStatus;
import xap.tutorial.user.model.User;

import com.gigaspaces.client.ReadByIdsResult;
import com.gigaspaces.document.SpaceDocument;
import com.gigaspaces.metadata.SpaceTypeDescriptor;
import com.gigaspaces.metadata.SpaceTypeDescriptorBuilder;
import com.gigaspaces.metadata.index.SpaceIndexType;
import com.j_spaces.core.client.SQLQuery;

@Service(IQSGService.QUERY_SERVICE)
public class QSGQueryService {

	@Autowired
	@Qualifier(IQSGService.SPACE)
	private GigaSpace space;

	public User findUserById() {
		return space.readById(User.class, new Long(1));
	}

	public User[] findUsersByIds() {

		ReadByIdsResult<User> result = space.readByIds(User.class, new Long[] {
				1L, 2L, 3L });
		return result.getResultsArray();
	}

	public User findUserByTemplate() {
		User user = new User();
		user.setName("John Dow");
		return space.read(user);
	}

	public User[] findUsersByTemplate() {
		User user = new User();
		user.setStatus(EAccountStatus.ACTIVE);
		return space.readMultiple(user);
	}

	public User[] sqlFindUsersByName() {
		SQLQuery<User> query = new SQLQuery<User>(User.class,
				"name = 'John Dow'");
		return space.readMultiple(query);
	}

	public User[] sqlFindUsersByNameAndCreditLimit() {
		SQLQuery<User> query = new SQLQuery<User>(User.class,
				"name = 'John Dow' AND creditLimit > 1000");
		return space.readMultiple(query);
	}

	public User[] sqlFindUsersByNameAndIds() {
		SQLQuery<User> query = new SQLQuery<User>(User.class,
				"name = 'John Dow' AND id IN(1L,3L,5L)");
		return space.readMultiple(query);
	}

	public User[] sqlParameterFindUsersByName() {
		SQLQuery<User> query = new SQLQuery<User>(User.class, "name = ?")
				.setParameter(1, "John Dow");
		return space.readMultiple(query);
	}

	public User[] sqlParameterFindUsersByNameAndCreditLimit() {
		SQLQuery<User> query = new SQLQuery<User>(User.class,
				"name = ? AND creditLimit > ?");
		query.setParameter(1, "John Dow");
		query.setParameter(2, new Double(1000));
		return space.readMultiple(query);
	}

	public User[] sqlFindUsersByZipCode() {
		SQLQuery<User> query = new SQLQuery<User>(User.class,
				"address.zipCode = 12345");
		return space.readMultiple(query);
	}

	public User sqlFindUserByPhoneNumber() {
		SQLQuery<User> query = new SQLQuery<User>(User.class,
				"contacts.home = '770-123-5555'");
		return space.read(query);
	}

	public User[] findUsersByNameAndProjection() {
		SQLQuery<User> query = new SQLQuery<User>(User.class, "name = ?");
		query.setParameter(1, "John Dow");
		query.setProjections("name");

		return space.readMultiple(query);
	}

	public User[] findUsersByrating() {
		SQLQuery<User> sqlQuery = new SQLQuery<User>(User.class,
				"ratings[*] = 1");
		return space.readMultiple(sqlQuery);
	}

	public User[] findUsersByGroup() {
		SQLQuery<User> sqlQuery = new SQLQuery<User>(User.class,
				"groups[*].id = 1L");
		return space.readMultiple(sqlQuery);
	}

	public User[] findUsersByComment() {
		return space.readMultiple(new SQLQuery<User>(User.class,
				"comment[*]='existing'"));
	}

	public User[] findUsersBySQLExpression() {
		SQLQuery<User> query = new SQLQuery<User>(User.class, "name like 'J%'");
		return space.readMultiple(query);
	}

	public User[] findUsersByRegularExpression() {
		// Match all entries of type User that have a name that starts with C or
		// R:
		SQLQuery<User> query = new SQLQuery<User>(User.class,
				"name rlike '(J|R).*'");
		return space.readMultiple(query);
	}

	public User[] findUsersByEnum() {
		SQLQuery<User> query = new SQLQuery<User>(User.class, "status = ?");
		query.setParameter(1, EAccountStatus.BLOCKED);
		return space.readMultiple(query);
	}

	public SpaceDocument readProductById() {
		SpaceDocument template = new SpaceDocument("Product");
		template.setProperty("CatalogNumber", "av-9876");
		return space.read(template);
	}

	public SpaceDocument readProductByTemplate() {
		SpaceDocument template = new SpaceDocument("Product");
		template.setProperty("Name", "Jet Propelled Pogo Stick");
		return space.read(template);
	}

	public SpaceDocument[] readProductsBySQL() {
		SQLQuery<SpaceDocument> query = new SQLQuery<SpaceDocument>("Product",
				"Price > ?");
		query.setParameter(1, 15.0f);
		return space.readMultiple(query);
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

}
