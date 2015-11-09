package edu.sjsu.cmpe275.lab2.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;



@XmlSeeAlso(value = {Organization.class, Address.class})
@XmlRootElement(name ="person")
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orgId")
    private Organization org;

    @XmlTransient
    @Transient
    private List<Integer> friends;


    /**
     * Getter for person ID
     *
     * @return
     */
    @XmlElement(name = "id")
    public long getId() {
        return id;
    }

    /**
     * Setter for person ID
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for first name
     *
     * @return
     */
    @XmlElement(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    /**
     * Setter for first name
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Getter for last name
     *
     * @return
     */
    @XmlElement(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    /**
     * Setter for last name
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Getter for person's email
     *
     * @return
     */
    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    /**
     * Setter for person's email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for person's description
     *
     * @return
     */
    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    /**
     * Setter for person's description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for person's address
     *
     * @return
     */
    @XmlElement(name = "address")
    public Address getAddress() {
        return address;
    }

    /**
     * Setter for person's address
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }


    /**
     * Getter for person's organization
     *
     * @return
     */
    @XmlElement(name = "org")
    public Organization getOrg() {
        return org;
    }

    /**
     * Setter for person's organization
     *
     * @param org
     */
    public void setOrg(Organization org) {
        this.org = org;
    }

    public List<Integer> getFriendsDetails() {
        return friends;
    }

    public void setFriendsDetails(List<Integer> friends) {
        this.friends = friends;
    }

    public List<Integer> getFriends() {
        return friends;
    }

    public void setFriends(List<Integer> friends) {
        this.friends = friends;
    }


}
