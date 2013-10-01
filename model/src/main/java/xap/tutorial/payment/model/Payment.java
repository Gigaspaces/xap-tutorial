package xap.tutorial.payment.model;

import java.io.Serializable;
import java.util.Date;

import com.gigaspaces.annotation.pojo.FifoSupport;
import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.metadata.index.SpaceIndexType;

@SpaceClass(fifoSupport = FifoSupport.ALL)
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Long userId;
	private Long merchantId;
	private String description;
	private Double paymentAmount;
	private ETransactionStatus status;
	private Date createdDate;

	public Payment(String paymentId) {
		this.id = paymentId;
	}

	public Payment() {
	}

	@SpaceId(autoGenerate = true)
	public String getPaymentId() {
		return id;
	}

	public void setPaymentId(String paymentId) {
		this.id = paymentId;
	}

	@SpaceIndex(type = SpaceIndexType.BASIC)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long payingAccountId) {
		this.userId = payingAccountId;
	}

	@SpaceRouting
	@SpaceIndex(type = SpaceIndexType.BASIC)
	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long receivingMerchantId) {
		this.merchantId = receivingMerchantId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ETransactionStatus getStatus() {
		return status;
	}

	public void setStatus(ETransactionStatus status) {
		this.status = status;
	}

	@SpaceIndex(type = SpaceIndexType.BASIC)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}
}
