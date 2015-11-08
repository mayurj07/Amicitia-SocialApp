package edu.sjsu.cmpe275.lab2.controller;


import com.google.gson.Gson;
import edu.sjsu.cmpe275.lab2.exception.EntityNotFound;
import edu.sjsu.cmpe275.lab2.model.*;
import edu.sjsu.cmpe275.lab2.service.OrganizationService;
import edu.sjsu.cmpe275.lab2.service.PersonService;
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
                    produces = {"application/xml", "application/json", "text/html"})
    public String getPersonInfo(@PathVariable(value = "id") int personId, ModelMap model) throws EntityNotFound {
        Person person = personService.getPersonInfo(personId);

        Gson gson = new Gson();
        String person_JSON = gson.toJson(person);
        //model.addAttribute("person",person);
        //return "person";

        model.addAttribute("person",person_JSON);
        System.out.println(person_JSON);
        return "personJSON";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity getOrganizationInformation(
            @PathVariable("id") String id) {
        try {
            Organization organization = this.organizationService.getOrganization(id);
            if (organization != null)
                return new ResponseEntity<>(organization, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Configuration
    @EnableWebMvc
    public class WebConfig extends WebMvcConfigurerAdapter {

        @Resource
        private Environment env;

        /**
         * Override default web configuration like media type, accept header, etc.
         */
        @Override
        public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            configurer.favorPathExtension(false).
                    favorParameter(true).
                    ignoreAcceptHeader(true).
                    useJaf(false).
                    defaultContentType(MediaType.APPLICATION_JSON).
                    mediaType("json", MediaType.APPLICATION_JSON).
                    mediaType("xml", MediaType.APPLICATION_XML).
                    mediaType("html", MediaType.TEXT_HTML);
        }}


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
        Organization org = new Organization();
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
