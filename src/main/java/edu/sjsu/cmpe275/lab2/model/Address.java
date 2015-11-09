package edu.sjsu.cmpe275.lab2.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	@Column(name="street")
	private String street;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;

    @Column(name="zip")
    private String zip;

	/**
	 * Getter for street address
	 *
	 * @return
     */
	public String getStreet() {
		return street;
	}

    /**
     * Setter for street address
     *
     * @param street
     */
	public void setStreet(String street) {
		this.street = street;
	}

    /**
     * Getter for city
     *
     * @return
     */
	public String getCity() {
		return city;
	}

    /**
     * Setter for city
     *
     * @param city
     */
	public void setCity(String city) {
		this.city = city;
	}

    /**
     * Getter for state
     *
     * @return
     */
	public String getState() {
		return state;
	}

    /**
     * Setter for state
     *
     * @param state
     */
	public void setState(String state) {
		this.state = state;
	}

    /**
     * Getter for Zip
     *
     * @return
     */
	public String getZip() {
		return zip;
	}

    /**
     * Setter for Zip
     * @param zip
     */
	public void setZip(String zip) {
		this.zip = zip;
	}
}
