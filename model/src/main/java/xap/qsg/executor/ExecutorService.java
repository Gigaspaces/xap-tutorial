package xap.qsg.executor;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import xap.tutorial.mergant.model.ECategoryType;
import xap.tutorial.mergant.model.Merchant;

import com.gigaspaces.async.AsyncFuture;

@SuppressWarnings("unused")
@Service(IExecutorService.SERVICE)
public class ExecutorService {

	@Autowired
	@Qualifier(IExecutorService.SPACE)
	private GigaSpace space;

	public void executeTask() throws InterruptedException, ExecutionException {
		MerchantUserTask task = new MerchantUserTask(2L);

		AsyncFuture<HashSet<Long>> result = space.execute(task);
		HashSet<Long> hashSet = result.get();
	}

	public void executeAsyncTask() throws InterruptedException {
		MerchantUserTask task = new MerchantUserTask(2L);
		AsyncListener l = new AsyncListener();
		
		space.execute(task, l);
	}

	public void executeTaskWithRouting() throws InterruptedException,
			ExecutionException {
		MerchantUserTask task = new MerchantUserTask(2L);

		// Route the task to partion 2
		AsyncFuture<HashSet<Long>> result = space.execute(task, 2);
		HashSet<Long> hashSet = result.get();
	}

	public void executeTaskWithRoutingPOJO() throws InterruptedException,
			ExecutionException {
		MerchantUserTask task = new MerchantUserTask(2L);

		Merchant merchant = new Merchant();
		merchant.setId(2L);

		AsyncFuture<HashSet<Long>> result = space.execute(task, merchant);
		HashSet<Long> hashSet = result.get();
	}

	public void executeTaskRoutingAnnoation() throws InterruptedException,
			ExecutionException {
		MerchantUserTask task = new MerchantUserTask(2L);

		AsyncFuture<HashSet<Long>> result = space.execute(task);
		HashSet<Long> hashSet = result.get();
	}

	public void executeDistributedTask() throws InterruptedException,
			ExecutionException {
		MerchantByCategoryTask task = new MerchantByCategoryTask(
				ECategoryType.AUTOMOTIVE);

		AsyncFuture<List<Merchant>> result = space.execute(task);
		List<Merchant> list = result.get();
	}
}
