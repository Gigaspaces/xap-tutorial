package xap.tutorial.mergant.model;

import java.util.HashMap;

import xap.tutorial.account.model.EAccountStatus;
import xap.tutorial.address.model.Address;
import xap.tutorial.address.model.EAddressType;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.metadata.index.SpaceIndexType;

@SpaceClass
public class Merchant {

	private Long id;
	private String name;
	private Double receipts;
	private Double feeAmount;
	private EProductType category;
	private EAccountStatus status;

	private HashMap<EAddressType, Address> addresses;

	public Merchant() {
	}

	@SpaceId(autoGenerate = false)
	@SpaceRouting
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@SpaceIndex(type = SpaceIndexType.BASIC)
	public EProductType getCategory() {

		return category;
	}

	public void setCategory(EProductType category) {
		this.category = category;
	}

	public EAccountStatus getStatus() {
		return status;
	}

	public void setStatus(EAccountStatus status) {
		this.status = status;
	}

	public void setReceipts(Double receipts) {
		this.receipts = receipts;
	}

	public Double getReceipts() {
		return receipts;
	}

	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public Double getFeeAmount() {
		return feeAmount;
	}

	// This means that each Address.zip in the Collection is indexed.
	// @SpaceIndex(path = "[*].zip")
	public HashMap<EAddressType, Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(HashMap<EAddressType, Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(EAddressType type, Address address) {
		if (addresses == null) {
			addresses = new HashMap<EAddressType, Address>();
		}

		addresses.put(type, address);
	}

}
