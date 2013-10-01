package xap.qsg.executor;

import java.util.HashSet;

import com.gigaspaces.async.AsyncFutureListener;
import com.gigaspaces.async.AsyncResult;

public class AsyncListener implements AsyncFutureListener<HashSet<Integer>> {

	public void onResult(AsyncResult<HashSet<Integer>> result) {
		System.out.println("Listener received result");
	}
}
