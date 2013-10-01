package xap.qsg.executor;

import java.util.HashSet;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.Task;
import org.openspaces.core.executor.TaskGigaSpace;

import xap.tutorial.payment.model.Payment;

import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.j_spaces.core.client.SQLQuery;

public class MerchantUserTask implements Task<HashSet<Long>> {
	private static final long serialVersionUID = 1L;
	private Long merchantId;
	
	@TaskGigaSpace
	private transient GigaSpace space;

	public MerchantUserTask(Long merchantId) {
		this.merchantId = merchantId;
	}

	public HashSet<Long> execute() throws Exception {
		SQLQuery<Payment> query = new SQLQuery<Payment>(Payment.class,
				"merchantId = ? ");
		query.setParameter(1, merchantId);

		Payment[] payments = space.readMultiple(query, Integer.MAX_VALUE);
		HashSet<Long> userIds = new HashSet<Long>();

		// Eliminate duplicate UserId
		if (payments != null) {
			for (int i = 0; i < payments.length; i++) {
				userIds.add(payments[i].getUserId());
			}
		}
		return userIds;
	}

	@SpaceRouting
	public Long getMerchantId() {
		return merchantId;
	}

}
