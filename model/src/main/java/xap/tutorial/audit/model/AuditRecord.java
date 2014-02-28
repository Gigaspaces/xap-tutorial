package xap.tutorial.audit.model;

import com.gigaspaces.document.SpaceDocument;

public class AuditRecord extends SpaceDocument {

	private static final String TYPE_NAME = "AuditRecord";
	private static final String ID = "id";
	private static final String TIMESTAMP = "timeStamp";
	private static final String USERNAME = "userName";
	private static final String APPLICATION = "application";
	private static final String CONTENT = "content";

	public AuditRecord() {
		super(TYPE_NAME);
	}

	public Long getId() {
		return super.getProperty(ID);
	}

	public void setId(Long id) {
		super.setProperty(ID, id);
	}

	public Long getTimeStamp() {
		return super.getProperty(TIMESTAMP);
	}

	public void setTimeStamp(Long timeStamp) {
		super.setProperty(TIMESTAMP, timeStamp);
	}

	public String getApplication() {
		return super.getProperty(APPLICATION);
	}

	public void setApplication(String application) {
		super.setProperty(APPLICATION, application);
	}

	public String getAuditContent() {
		return super.getProperty(CONTENT);
	}

	public void setAuditContent(String auditContent) {
		super.setProperty(CONTENT, auditContent);
	}

	public String getUserName() {
		return super.getProperty(USERNAME);
	}

	public void setUserName(String userName) {
		super.setProperty(USERNAME, userName);
	}

}
