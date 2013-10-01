package xap.qsg.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.DistributedTask;
import org.openspaces.core.executor.TaskGigaSpace;

import xap.tutorial.mergant.model.ECategoryType;
import xap.tutorial.mergant.model.Merchant;

import com.gigaspaces.async.AsyncResult;
import com.j_spaces.core.client.SQLQuery;

 
public class MerchantByCategoryTask implements
		DistributedTask<Merchant[], List<Merchant>> {

	private static final long serialVersionUID = 1L;

	private ECategoryType categoryType;
	@TaskGigaSpace
	private transient GigaSpace gigaSpace;

	public MerchantByCategoryTask(ECategoryType categoryType) {
		this.categoryType = categoryType;
	}
	
	public Merchant[] execute() throws Exception {
		SQLQuery<Merchant> query = new SQLQuery<Merchant>(Merchant.class,
				"category = ?");
		query.setParameter(1, categoryType);
		return gigaSpace.readMultiple(query, Integer.MAX_VALUE);
	}

	public List<Merchant> reduce(List<AsyncResult<Merchant[]>> results)
			throws Exception {
		List<Merchant> list = new ArrayList<Merchant>();

		for (AsyncResult<Merchant[]> result : results) {
			if (result.getException() != null) {
				throw result.getException();
			}
			Collections.addAll(list, result.getResult());
		}
		return list;
	}
}
