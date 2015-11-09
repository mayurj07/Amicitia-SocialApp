package edu.sjsu.cmpe275.lab2.model;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "friendship",
            joinColumns = {@JoinColumn(name = "person1", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "person2", referencedColumnName = "id")}
            )
    private List<Person> friends;


    /**
     * Getter for person ID
     *
     * @return
     */
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
     * Getter for friends list
     *
     * @return
     */
    public List<Person> getFriends() {
        return friends;
    }

    /**
     * Setter for friends list
     *
     * @param friends
     */
    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    /**
     * Getter for person's organization
     *
     * @return
     */
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
}
