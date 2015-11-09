package edu.sjsu.cmpe275.lab2.controller;


import edu.sjsu.cmpe275.lab2.exception.OrganizationNotFoundException;
import edu.sjsu.cmpe275.lab2.model.Address;
import edu.sjsu.cmpe275.lab2.model.Organization;
import edu.sjsu.cmpe275.lab2.service.OrganizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/org")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;


    /**
     * GET organization in HTML format by ID
     * */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"text/html"})
    public String getOrganizationHTML(@PathVariable("id") int id, ModelMap model) {
        Organization orgn = organizationService.findById(id);
        model.addAttribute("org", orgn);
        return "organization";
    }

    /**
     * GET organization in JSON/XML format by ID
     * */
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<Organization> getOrganization(@PathVariable("id") int id, ModelMap model) {
        Organization orgObj = organizationService.findById(id);

        if (orgObj == null) {
            return new ResponseEntity<Organization>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Organization>(orgObj, HttpStatus.OK);
    }

    /**
     * Create an Organization
     * */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Organization> createOrganization(@RequestParam(value = "name", required = true) String name,
                                                           @RequestParam(value = "description", defaultValue = "") String description,
                                                           @RequestParam(value = "state", defaultValue = "") String state,
                                                           @RequestParam(value = "city", defaultValue = "") String city,
                                                           @RequestParam(value = "street", defaultValue = "") String street,
                                                           @RequestParam(value = "zip", defaultValue = "") String zip) {
        Address address = new Address();
        address.setCity(city);
        address.setState(state);
        address.setStreet(street);
        address.setZip(zip);

        Organization objectOrg = new Organization();
        objectOrg.setName(name);
        objectOrg.setDescription(description);
        objectOrg.setAddress(address);

        Organization newOrgObj = organizationService.saveOrganization(objectOrg);
        return new ResponseEntity<Organization>(newOrgObj, HttpStatus.OK);
    }

    /**
     * Delete Organization*/
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<Organization> deleteOrganization(@PathVariable int id) throws Exception {


        Organization orgToDelete = organizationService.findById(id);
        if(orgToDelete == null){
            return new ResponseEntity<Organization>(HttpStatus.NOT_FOUND);
        }

        Organization deletedOrg = null;
        try {
            deletedOrg = organizationService.deleteOrganizationbyId(id);
        } catch (Exception e) {
            return new ResponseEntity<Organization>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Organization>(deletedOrg, HttpStatus.OK);
    }

    /**
     * Update an Organization
     */
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.POST)
    public ResponseEntity<Organization> updateOrganization(@PathVariable("id") int id,
                                                           @RequestParam(value = "name") String name,
                                                           @RequestParam(value = "description", defaultValue = "") String description,
                                                           @RequestParam(value = "state", defaultValue = "") String state,
                                                           @RequestParam(value = "city", defaultValue = "") String city,
                                                           @RequestParam(value = "street", defaultValue = "") String street,
                                                           @RequestParam(value = "zip", defaultValue = "") String zip) {

        if(name == null || name.isEmpty())
            return new ResponseEntity<Organization>(HttpStatus.BAD_REQUEST);

        // get the organization with the given id
        Organization org = organizationService.findById(id);
        if (org == null) {
            return new ResponseEntity<Organization>(HttpStatus.NOT_FOUND);
        }
        Address address = org.getAddress();
        org.setName(name);

        if (state != null && !state.isEmpty())
            address.setState(state);

        if (city != null && !city.isEmpty())
            address.setCity(city);

        if (zip != null && !zip.isEmpty())
            address.setZip(zip);

        if (street != null && !street.isEmpty())
            address.setStreet(street);

        if (description != null && !description.isEmpty())
            org.setDescription(description);

        Organization updatedOrg = organizationService.updateOrganization(org);
        return new ResponseEntity<Organization>(updatedOrg, HttpStatus.OK);
    }

}
