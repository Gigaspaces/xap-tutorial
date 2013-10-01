package xap.qsg.cassandra;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import xap.tutorial.audit.model.AuditRecord;

public class CassandraTest {

	static String url = "jini://*/*/auditSpace";
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GigaSpace space = new GigaSpaceConfigurer(new UrlSpaceConfigurer(url))
				.gigaSpace();

		for (int i = 0; i < 10; i++) {
			AuditRecord audit = new AuditRecord();
			audit.setId(new Long(i));
			audit.setApplication("Financial Application");
			audit.setUserName("user" + i);
			audit.setAuditContent("<Transaction id =100 Amount= 200.00 US$");
			audit.setTimeStamp(new Long(123456744));

			space.write(audit);
		}
	}

}
