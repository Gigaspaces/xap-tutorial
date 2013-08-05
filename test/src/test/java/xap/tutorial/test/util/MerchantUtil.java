package xap.tutorial.test.util;

import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import xap.tutorial.account.model.EAccountStatus;
import xap.tutorial.mergant.model.Merchant;
import xap.tutorial.mergant.service.IMerchantService;

@Service
public class MerchantUtil {

	@Autowired
	@Qualifier(IMerchantService.SPACE)
	private GigaSpace gigaSpace;

	public void loadMerchant() {

		gigaSpace.write(this.createMerchant());

	}

	public Merchant createMerchant() {

		Merchant m = new Merchant();
		m.setId(new Long(1));
		m.setFeeAmount(new Double(10.00));
		m.setName("Mechant 1");
		m.setStatus(EAccountStatus.ACTIVE);

		return m;
	}
}
