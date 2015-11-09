package edu.sjsu.cmpe275.lab2.controller;


import com.google.gson.Gson;
import edu.sjsu.cmpe275.lab2.dao.OrganizationDao;
import edu.sjsu.cmpe275.lab2.exception.EntityNotFound;
import edu.sjsu.cmpe275.lab2.exception.OrganizationNotFoundException;
import edu.sjsu.cmpe275.lab2.model.*;
import edu.sjsu.cmpe275.lab2.model.Organization;
import edu.sjsu.cmpe275.lab2.service.OrganizationService;
import edu.sjsu.cmpe275.lab2.service.PersonService;
import edu.sjsu.cmpe275.lab2.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @RequestMapping(method = RequestMethod.POST)
    public Person createPerson(@RequestParam(value = "firstname", required = true) String firstname,
                               @RequestParam(value = "lastname", required = true) String lastname,
                               @RequestParam(value = "email", required = true) String email,
                               @RequestParam(value = "description", defaultValue = "") String description,
                               @RequestParam(value = "street", defaultValue = "") String street,
                               @RequestParam(value = "city", defaultValue = "") String city,
                               @RequestParam(value = "state", defaultValue = "state") String state,
                               @RequestParam(value = "zip", defaultValue = "") String zip,
                               @RequestParam(value = "organization", defaultValue = "") String orgid,
                               ModelMap model) throws Exception {


        Person personObj = new Person();
        Address addressObj = new Address();
        Organization orgObj = null;
        List<Person> friendList = new ArrayList<Person>();

        if (firstname == null || "".equalsIgnoreCase(firstname)
                || lastname == null || "".equalsIgnoreCase(lastname)
                || email == null || "".equalsIgnoreCase(email)) {
            throw new Exception("required");
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

        orgObj = orgService.findById(Integer.parseInt(orgid));
        if (orgObj != null) {
            personObj.setOrg(orgObj);
        } else {
            personObj.setOrg(null);
        }
        personService.createPerson(personObj);
        model.addAttribute("person", personObj);
        return personObj;
    }

    @RequestMapping(value = "/{id}",
                    method = RequestMethod.GET,
                    produces = {"text/html"})
    public String getPersonHTML(@PathVariable(value = "id") int personId, ModelMap model) throws EntityNotFound {
        Person person = personService.getPersonInfo(personId);
        model.addAttribute("person",person);
        return "person";
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Person> getPerson(@PathVariable(value = "id") int personId) throws EntityNotFound {
        try{
            Person person = personService.getPersonInfo(personId);
            if(person != null)
                return new ResponseEntity<Person>(person, HttpStatus.OK);
            else
                return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updatePersonInfo(
            @PathVariable(value = "id") int personId,
            @RequestParam(value = "firstname", defaultValue = "") String firstname,
            @RequestParam(value = "lastname", defaultValue = "") String lastname,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "description", defaultValue = "") String description,
            @RequestParam(value = "street", defaultValue = "") String street,
            @RequestParam(value = "city", defaultValue = "") String city,
            @RequestParam(value = "state", defaultValue = "") String state,
            @RequestParam(value = "zip", defaultValue = "") String zip,
            @RequestParam(value = "id", defaultValue = "") int orgId,
            ModelMap model) throws Exception {

            System.out.println("personId:    " +personId);
            Person person = personService.getPersonInfo(personId);
            Organization org = new Organization();



        if(person == null )
             		{
                        throw new Exception("Unable to find Person Id....");
             		}

       /* if (firstname == null || "".equalsIgnoreCase(firstname)
                || lastname == null || "".equalsIgnoreCase(lastname)
                || email == null || "".equalsIgnoreCase(email) || description == null || "".equalsIgnoreCase(description)
                || street == null || "".equalsIgnoreCase(street) || city == null || "".equalsIgnoreCase(city)
                || state == null || "".equalsIgnoreCase(state) || zip == null || "".equalsIgnoreCase(zip)) {

            return person;
        }*/
//        Person per = personService.getPersonInfo(personId);
//        Person entity = personDao.getPersonInfo((int) updatedPerson.getId());
//          Person per = personService.getPersonInfo((int) updatePersonInfo)


         else {


            Address address = person.getAddress();
            person.setEmail(email);
            if (firstname != null && "".equalsIgnoreCase(firstname))
                person.setFirstname(firstname);
            if (lastname != null && "".equalsIgnoreCase(lastname))
                person.setLastname(lastname);
            if (email != null && "".equalsIgnoreCase(email))
                person.setEmail(email);
            if (description != null && "".equalsIgnoreCase(description))
                person.setDescription(description);
            if (street != null && "".equalsIgnoreCase(street))
                address.setStreet(street);
            if (city != null && "".equalsIgnoreCase(city))
                address.setCity(city);
            if (state != null && "".equalsIgnoreCase(state))
                address.setState(state);
            if (zip != null && "".equalsIgnoreCase(zip))
                address.setZip(zip);



            Organization orgObj = orgService.findById(orgId);
            		if(orgObj==null) throw new OrganizationNotFoundException(" organization ");
            else  {
                        person.setOrg(orgObj);
                    }

            Person updatedPerson = personService.updatePersonInfo(person);
            model.addAttribute("person", updatedPerson);
        }
        return "person";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Person deletePerson(@RequestParam(value = "id") int personId) throws EntityNotFound {
        personService.deletePerson(personId);
        return new Person();
    }

}
