package xap.tutorial.account.model;

import xap.tutorial.mergant.model.ECategoryType;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;

@SpaceClass
public class Account {
	private Long id;
	private String number;
	private Double receipts;
	private Double feeAmount;
	private ECategoryType category;
	private EAccountStatus status;
	
	@SpaceId
	@SpaceRouting
	public Long getId() {
		return id;
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
	public ECategoryType getCategory() {
		return category;
	}
	public void setCategory(ECategoryType category) {
		this.category = category;
	}
	public EAccountStatus getStatus() {
		return status;
	}
	public void setStatus(EAccountStatus status) {
		this.status = status;
	}
}
