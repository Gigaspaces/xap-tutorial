package xap.tutorial.test.util;

import org.openspaces.core.GigaSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import xap.tutorial.mergant.service.IMerchantService;


@Service
public class SpaceUtility {

	@Autowired
	@Qualifier(IMerchantService.SPACE)
	private GigaSpace space;
	
	public void clear(Object template)
	{
		space.clear(template);
	}
}
