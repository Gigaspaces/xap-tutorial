package xap.qsg.test.spring;

import org.junit.Test;
import org.openspaces.core.GigaSpace;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import xap.tutorial.test.util.AbstractTutorialContextTests;

public class SpringTest extends AbstractTutorialContextTests {

	@Test
	public void findSpace()  {

		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(
				"classpath:/spring/application-context.xml");

		@SuppressWarnings("unused")
		GigaSpace space = (GigaSpace) context.getBean("xapTutorialSpace");

	}

}
