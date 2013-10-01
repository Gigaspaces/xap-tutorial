package xap.qsg.spring;

import org.openspaces.core.GigaSpace;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringService {

	public static void main(String[] args) {
		// Load the Spring context. After initialization the
		// afterPropertiesSet() method is called.
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(
				"application_context.xml");
		
		@SuppressWarnings("unused")
		GigaSpace space = (GigaSpace)context.getBean("space");

	}
}
