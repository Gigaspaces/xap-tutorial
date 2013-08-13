package xap.tutorial.mergant.model;

import xap.tutorial.account.model.EAccountStatus;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceDynamicProperties;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.document.DocumentProperties;
import com.gigaspaces.metadata.index.SpaceIndexType;

@SpaceClass
public class Merchant {

	private Long id;
	private String name;
	private Double receipts;
	private Double feeAmount;
	private ECategoryType category;
	private EAccountStatus status;
	private DocumentProperties extraInfo;

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

	@SpaceDynamicProperties
	public DocumentProperties getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(DocumentProperties extraInfo) {
		this.extraInfo = extraInfo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@SpaceIndex(type = SpaceIndexType.BASIC)
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

}
