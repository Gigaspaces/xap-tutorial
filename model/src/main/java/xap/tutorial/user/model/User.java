package xap.tutorial.user.model;

import xap.tutorial.account.model.EAccountStatus;
import xap.tutorial.address.model.Address;
import xap.tutorial.address.model.EAddressType;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.document.DocumentProperties;
import com.gigaspaces.metadata.index.SpaceIndexType;

@SpaceClass
public class User {

	private Integer id;
	private String name;
	private Double balance;
	private Double creditLimit;
	private EAccountStatus status;
	private DocumentProperties addresses;

	public User(Integer id) {
		this.id = id;
	}

	public User() {
	}

	@SpaceId(autoGenerate = false)
	@SpaceRouting
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SpaceIndex(type = SpaceIndexType.BASIC)
	public String getName() {
		return name;
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

	@SpaceIndex(type = SpaceIndexType.EXTENDED)
	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setStatus(EAccountStatus status) {
		this.status = status;
	}

	public EAccountStatus getStatus() {
		return status;
	}

	public void addAddress(EAddressType type, Address address) {
		if (addresses == null) {
			addresses = new DocumentProperties();
		}
		addresses.put(type.toString(), address);
	}

	public Address getAddress(EAddressType type) {
		return (Address) addresses.get(type.toString());
	}

	public DocumentProperties getAddresses() {
		return addresses;
	}

	public void setAddresses(DocumentProperties addresses) {
		this.addresses = addresses;
	}
}
