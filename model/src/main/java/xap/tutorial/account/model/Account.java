package xap.tutorial.account.model;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.annotation.pojo.SpaceVersion;

@SpaceClass
public class Account {
	private Long id;
	private String number;
	private Double receipts;
	private Double feeAmount;
	private EAccountStatus status;
	private int version;

	@SpaceId
	@SpaceRouting
	public Long getId() {
		return id;
	}

	@SpaceVersion
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getReceipts() {
		return receipts;
	}

	public void setReceipts(Double receipts) {
		this.receipts = receipts;
	}

	public Double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}

 
	public EAccountStatus getStatus() {
		return status;
	}

	public void setStatus(EAccountStatus status) {
		this.status = status;
	}
}
