package xap.tutorial.mergant.service;

import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import xap.tutorial.mergant.model.Merchant;

@Service(IMerchantService.SERVICE)
public class MerchantQueryService {

	@Autowired
	@Qualifier(IMerchantService.SPACE)
	private GigaSpace space;

	// Return a collection of Merchants
	public Merchant[] findAllMerchants() {

		Merchant template = new Merchant();
		return space.readMultiple(template);
	}

	// Find Merchant by id
	public Merchant findMerchantById(Long id) {
		return space.readById(Merchant.class, id);
	}
}
