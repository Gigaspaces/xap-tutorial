package xap.qsg.transaction;

import java.util.Date;

import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xap.tutorial.payment.model.ETransactionStatus;
import xap.tutorial.payment.model.Payment;

import com.gigaspaces.client.ReadModifiers;

@Service(ITransactionService.SERVICE)
public class TransactionService {

	@Autowired
	@Qualifier(ITransactionService.SPACE)
	private GigaSpace space;

	@Transactional
	public void createPayment() {
		Payment payment = new Payment();
		payment.setCreatedDate(new Date(System.currentTimeMillis()));
		payment.setUserId(new Long(1));
		payment.setStatus(ETransactionStatus.PROCESSED);

		space.write(payment);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void createNewPayment() {
		Payment payment = new Payment();
		payment.setCreatedDate(new Date(System.currentTimeMillis()));
		payment.setUserId(new Long(1));
		payment.setStatus(ETransactionStatus.PROCESSED);

		space.write(payment);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void executePayment(Integer orderIDs[]) throws Exception {
		// Read and lock the payment object
		Payment payment = space.readById(Payment.class, 1L,
				ReadModifiers.EXCLUSIVE_READ_LOCK);
		
		payment.setStatus(ETransactionStatus.CANCELLED);
		space.write(payment);
	}

}
