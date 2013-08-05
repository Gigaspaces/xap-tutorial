package xap.tutorial.user.model;

import xap.tutorial.account.model.EAccountStatus;
import xap.tutorial.address.model.Address;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.metadata.index.SpaceIndexType;

@SpaceClass
public class User {

	private Integer id;
	private String name;
	private Double balance;
	private Double creditLimit;
	private EAccountStatus status;
	private Address address;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
