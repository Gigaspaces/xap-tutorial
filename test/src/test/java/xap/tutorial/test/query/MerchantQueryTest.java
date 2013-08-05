package xap.tutorial.test.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xap.tutorial.mergant.model.Merchant;
import xap.tutorial.mergant.service.MerchantQueryService;
import xap.tutorial.test.util.AbstractTutorialContextTests;
import xap.tutorial.test.util.MerchantUtil;
import xap.tutorial.test.util.SpaceUtility;

public class MerchantQueryTest extends AbstractTutorialContextTests {

	@Autowired
	private SpaceUtility spaceUtility;

	@Autowired
	private MerchantQueryService service;

	@Autowired
	private MerchantUtil merchantUtil;

	@Before
	public void init() {
		merchantUtil.loadMerchant();
	}

	@After
	public void clear() {
		spaceUtility.clear(new Merchant());
	}

	@Test
	public void findAllMerchants() {

		Merchant[] merchants = service.findAllMerchants();

		assertNotNull(merchants);
		assertEquals(1, merchants.length);
	}

	@Test
	public void findMerchantById() {

		Merchant merchant = service.findMerchantById(new Long(1));

		assertNotNull(merchant);
		assertEquals(new Long(1), merchant.getId());
	}

}
