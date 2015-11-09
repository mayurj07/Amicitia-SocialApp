package edu.sjsu.cmpe275.lab2.controller;


import edu.sjsu.cmpe275.lab2.exception.EntityNotFound;
import edu.sjsu.cmpe275.lab2.model.*;
import edu.sjsu.cmpe275.lab2.model.Organization;
import edu.sjsu.cmpe275.lab2.service.OrganizationService;
import edu.sjsu.cmpe275.lab2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;
    @Autowired
    OrganizationService orgService;

    /**
     * Create a Person by passing the following parameters
     *
     * @param firstname
     * @param lastname
     * @param email
     * @param description
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param orgid
     * @return
     * @throws Exception
     */

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Person> createPerson(@RequestParam(value = "firstname", required = true) String firstname,
                                               @RequestParam(value = "lastname", required = true) String lastname,
                                               @RequestParam(value = "email", required = true) String email,
                                               @RequestParam(value = "description", defaultValue = "") String description,
                                               @RequestParam(value = "street", defaultValue = "") String street,
                                               @RequestParam(value = "city", defaultValue = "") String city,
                                               @RequestParam(value = "state", defaultValue = "state") String state,
                                               @RequestParam(value = "zip", defaultValue = "") String zip,
                                               @RequestParam(value = "organization", defaultValue = "") String orgid) {


        Person personObj = new Person();
        Address addressObj = new Address();
        Organization orgObj = null;
        List<Integer> friendList = new ArrayList<Integer>();

        if (firstname == null || "".equalsIgnoreCase(firstname)
                || lastname == null || "".equalsIgnoreCase(lastname)
                || email == null || "".equalsIgnoreCase(email)) {
            return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
        }

        personObj.setFirstname(firstname);
        personObj.setLastname(lastname);
        personObj.setEmail(email);
        personObj.setDescription(description);

        addressObj.setCity(city);
        addressObj.setState(state);
        addressObj.setStreet(street);
        addressObj.setZip(zip);
        personObj.setAddress(addressObj);

        personObj.setFriends(friendList);


        /**
         * if invalid org id then send bad request
         * */

        if (orgid != null || !"".equalsIgnoreCase(orgid)) {
            orgObj = orgService.findById(Integer.parseInt(orgid));
            if (orgObj == null) {
                return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
            } else {
                personObj.setOrg(orgObj);
            }
        }

        Person createdPerson = personService.createPerson(personObj);

        return new ResponseEntity<Person>(createdPerson, HttpStatus.OK);
    }

    /**
     * Get a person by Id in HTML format
     *
     * @param personId
     * @param model
     * @return
     * @throws EntityNotFound
     */

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"text/html"})
    public String getPersonHTML(@PathVariable(value = "id") int personId, ModelMap model) throws EntityNotFound {
        Person person = personService.getPersonInfo(personId);
        if (person == null) {
            throw new EntityNotFound("Person Not Found.");
        }
        model.addAttribute("person", person);
        return "person";
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Person> getPerson(@PathVariable(value = "id") int personId) throws EntityNotFound {
        try {
            Person person = personService.getPersonInfo(personId);
            if (person != null)
                return new ResponseEntity<Person>(person, HttpStatus.OK);
            else
                return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update a person by id
     *
     * @param personId
     * @param email
     * @param firstname
     * @param lastname
     * @param description
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param orgid
     * @return Person in JSON
     * @throws Exception
     */

    @RequestMapping(value = "/{personId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Person> updatePerson(
            @PathVariable(value = "personId") int personId,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "firstname", required = true) String firstname,
            @RequestParam(value = "lastname", required = true) String lastname,
            @RequestParam(value = "description", defaultValue = "") String description,
            @RequestParam(value = "street", defaultValue = "") String street,
            @RequestParam(value = "city", defaultValue = "") String city,
            @RequestParam(value = "state", defaultValue = "") String state,
            @RequestParam(value = "zip", defaultValue = "") String zip,
            @RequestParam(value = "organization", defaultValue = "") String orgid) throws Exception {

        Person person = personService.getPersonInfo(personId);
        if (person == null) {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }

        Address address = person.getAddress();
        person.setEmail(email);

        if (firstname != null || !"".equalsIgnoreCase(firstname))
            person.setFirstname(firstname);
        if (lastname != null || !"".equalsIgnoreCase(lastname))
            person.setLastname(lastname);
        if (description != null || !"".equalsIgnoreCase(description))
            person.setDescription(description);
        if (street != null || !"".equalsIgnoreCase(street))
            address.setStreet(street);
        if (city != null || !"".equalsIgnoreCase(city))
            address.setCity(city);
        if (state != null || !"".equalsIgnoreCase(state))
            address.setState(state);
        if (zip != null || !"".equalsIgnoreCase(zip))
            address.setZip(zip);

        if (orgid != null || !"".equalsIgnoreCase(orgid)) {
            Organization orgObj = orgService.findById(Integer.parseInt(orgid));
            if (orgObj == null) {
                return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
            } else {
                person.setOrg(orgObj);
            }
        }

        Person updatedPerson = personService.updatePersonInfo(person);
        return new ResponseEntity<Person>(updatedPerson, HttpStatus.OK);
    }


    /**
     * Delete a person
     *
     * @param personId
     * @return
     * @throws EntityNotFound
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Person> deletePerson(@PathVariable(value = "id") int personId) throws EntityNotFound {

        Person personToDelete = personService.getPersonInfo(personId);
        if (personToDelete == null)
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);

        Person deletedPerson = personService.deletePerson(personId);
        return new ResponseEntity<Person>(deletedPerson, HttpStatus.OK);
    }

}
