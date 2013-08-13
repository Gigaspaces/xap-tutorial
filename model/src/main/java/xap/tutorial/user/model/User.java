package xap.tutorial.user.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xap.tutorial.account.model.EAccountStatus;
import xap.tutorial.contact.model.Address;
import xap.tutorial.contact.model.EContactType;

import com.gigaspaces.annotation.pojo.CompoundSpaceIndex;
import com.gigaspaces.annotation.pojo.CompoundSpaceIndexes;
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.metadata.index.SpaceIndexType;

@CompoundSpaceIndexes({ @CompoundSpaceIndex(paths = { "name", "creditLimit" }) })
@SpaceClass
public class User {
	private Long id;
	private String name;
	private Double balance;
	private Double creditLimit;
	private EAccountStatus status;
	private Address address;
	private String[] comment;
	private Map<String, String> contacts;
	private List<Group> groups;
	private List<Integer> ratings;

	public User() {
	}

	@SpaceId(autoGenerate = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SpaceIndex(type = SpaceIndexType.BASIC)
	public String getName() {
		return name;
	}

	@SpaceIndex(type = SpaceIndexType.EXTENDED)
	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getBalance() {
		return balance;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public void setStatus(EAccountStatus status) {
		this.status = status;
	}

	public EAccountStatus getStatus() {
		return status;
	}

	@SpaceIndex(path = "zipCode", type = SpaceIndexType.BASIC)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@SpaceIndex(path = "home")
	public Map<String, String> getContacts() {
		return contacts;
	}

	public void setContacts(Map<String, String> contacts) {
		this.contacts = contacts;
	}

	public void addContact(EContactType type, String contact) {
		if (contacts == null) {
			contacts = new HashMap<String, String>();
		}
		contacts.put(type.toString(), contact);
	}

	@SpaceIndex(path = "[*].id")
	// This means that each Group.id in the Collection is indexed.
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@SpaceIndex(path = "[*]")
	public List<Integer> getRatings() {
		return ratings;
	}

	public void setRatings(List<Integer> ratings) {
		this.ratings = ratings;
	}

	@SpaceIndex(path = "[*]")
	public String[] getComment() {
		return comment;
	}

	public void setComment(String[] comment) {
		this.comment = comment;
	}

}
