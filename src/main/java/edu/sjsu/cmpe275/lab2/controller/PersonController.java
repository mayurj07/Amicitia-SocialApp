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


 /*   @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
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

        *//**
         * Override default web configuration like media type, accept header, etc.
         *//*
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
        }}*/


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
            Address address = new Address();

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

        if(firstname != null && "".equalsIgnoreCase(firstname))
              person.setFirstname(firstname);
        if (lastname != null && "".equalsIgnoreCase(lastname))
              person.setLastname(lastname);
        if(email != null && "".equalsIgnoreCase(email))
              person.setEmail(email);
        if (description != null && "".equalsIgnoreCase(description))
              person.setDescription(description);
        if(street != null && "".equalsIgnoreCase(street))
            address.setStreet(street);
        if (city != null && "".equalsIgnoreCase(city))
            address.setCity(city);
        if(state != null && "".equalsIgnoreCase(state))
                    address.setState(state);
        if (zip != null && "".equalsIgnoreCase(zip))
                    address.setZip(zip);

//        person.setOrg(org);

        Person updatedPerson = personService.updatePersonInfo(person);
        model.addAttribute("person", updatedPerson);
        return "person";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Person deletePerson(@RequestParam(value = "id") int personId) throws EntityNotFound {
        personService.deletePerson(personId);
        return new Person();
    }

}
