package xap.tutorial.contact.model;

public enum EContactType {
	MOBILE ("mobile"), BUSINESS("business") , HOME("home") ;

	private final String value;

	private EContactType(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return getValue();
	}
}
