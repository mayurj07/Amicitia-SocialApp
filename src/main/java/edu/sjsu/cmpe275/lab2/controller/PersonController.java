package edu.sjsu.cmpe275.lab2.controller;


import edu.sjsu.cmpe275.lab2.exception.EntityNotFound;
import edu.sjsu.cmpe275.lab2.model.*;
import edu.sjsu.cmpe275.lab2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

   /* @RequestMapping(value = "/greeting", produces = {"application/xml", "application/json"})
    @ResponseBody
    public Person greeting(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name,
            Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return person;
    }*/

   /* @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String htmlView(@RequestParam(value = "name") String name, Model model) {
        return "person";
    }*/

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestParam(value = "firstname", required = true) String firstname,
                               @RequestParam(value = "lastname", required = true) String lastname,
                               @RequestParam(value = "email", required = true) String email,
                               @RequestParam(value = "description", defaultValue = "") String description,
                               @RequestParam(value = "street", defaultValue = "") String street,
                               @RequestParam(value = "city", defaultValue = "") String city,
                               @RequestParam(value = "state", defaultValue = "state") String state,
                               @RequestParam(value = "zip", defaultValue = "") String zip,
                               @RequestParam(value = "organization", defaultValue = "") int orgid ) {

        person.setAddress(address);
        person.setOrg(org);

        personService.createPerson(person);

        return person;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {
            "application/xml", "application/json"})
    public Person getPersonInfo(@PathVariable int personId) throws EntityNotFound {
        personService.getPersonInfo(personId);
        return new Person();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Person updatePersonInfo(
            @PathVariable int personId,
            @RequestParam(value = "firstname", required = true) String firstName,
            @RequestParam(value = "lastName", required = true) String lastName,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "street", required = false) String street,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "zip", required = false) String zip,
            @RequestParam(value = "id", required = false) int orgId) throws EntityNotFound {

        Address address = new Address();
        address.setCity(city);
        address.setState(state);
        address.setStreet(street);
        address.setZip(zip);
        Organisation org = new Organisation();
        org.setId(orgId);
        Person person = new Person();
        person.setFirstname(firstName);
        person.setLastname(lastName);
        person.setEmail(email);
        person.setDescription(description);
        person.setAddress(address);
        person.setOrg(org);

        personService.updatePersonInfo(person);
        return person;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Person deletePerson(@RequestParam(value = "id") int personId) throws EntityNotFound {
        personService.deletePerson(personId);
        return new Person();
    }

}
