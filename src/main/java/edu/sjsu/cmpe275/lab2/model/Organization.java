package edu.sjsu.cmpe275.lab2.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso(value = { Address.class})
@XmlRootElement(name ="address")
@Entity
@Table(name="organization")
public class Organization {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="description")
	private String description;

	@Embedded
	private Address address;

	/**
	 * Getter for organization ID
	 *
	 * @return
     */
    @XmlElement(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * Getter for organization address
	 *
	 * @return
     */
    @XmlElement(name = "address")
	public Address getAddress() {
		return address;
	}

	/**
	 * Setter for organization address
     *
	 * @param address
     */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Setter for organization ID
	 *
	 * @param id
     */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for organization name
	 *
	 * @return
     */
    @XmlElement(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Setter for organization name
	 *
	 * @param name
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for organization description
	 *
	 * @return
     */
    @XmlElement(name = "description")
	public String getDescription() {
		return description;
	}

	/**
	 * Setter for organization description
	 * @param description
     */
	public void setDescription(String description) {
		this.description = description;
	}
}
