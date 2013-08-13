package xap.tutorial.contact.model;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	private String street;
	private String city;
	private String state;
	private ECountry country;
	private Integer zipCode;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ECountry getCountry() {
		return country;
	}

	public void setCountry(ECountry country) {
		this.country = country;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

}