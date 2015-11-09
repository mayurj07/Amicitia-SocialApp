package edu.sjsu.cmpe275.lab2.controller;


import edu.sjsu.cmpe275.lab2.exception.OrganizationNotFoundException;
import edu.sjsu.cmpe275.lab2.model.Address;
import edu.sjsu.cmpe275.lab2.model.Organization;
import edu.sjsu.cmpe275.lab2.service.OrganizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@RequestMapping(value="org/{id}", method=RequestMethod.GET)
	public String getOrganization(@PathVariable("id") int id,ModelMap model)
	{	
		System.out.println(" is is ::::::::::"+id);
		//Integer itentifier = Integer.valueOf(id);
		
		Organization orgn = organizationService.findById(id);
		System.out.println(" :::::::;;; test:is controller: "+orgn.getName());
		model.addAttribute("organization",orgn);
		return "allemployees";
	}
	
	@RequestMapping(value={"org"}, method=RequestMethod.POST)
	public String createOrganization(@RequestParam(value = "name",required=true) String name,
									 @RequestParam(value = "description", defaultValue = "") String description,
									 @RequestParam(value = "state",defaultValue = "") String state,
									 @RequestParam(value= "city",defaultValue = "") String city,
									 @RequestParam(value = "street", defaultValue = "") String street,
									 @RequestParam(value = "zip", defaultValue = "") String zip,
									 ModelMap model)
	{	
		Address address = new Address();
		address.setCity(city);
		address.setState(state);
		address.setStreet(street);
		address.setZip(zip);
		System.out.println("::: in controller :: "+address.getState()+" "+address.getCity());
		
		Organization objectOrg = new Organization();
		objectOrg.setName(name);
		objectOrg.setDescription(description);
		objectOrg.setAddress(address);
		
		System.out.println("::: in controller :: "+objectOrg.getName()+" "+objectOrg.getDescription());
		Organization result = organizationService.saveOrganization(objectOrg);
		model.addAttribute("result",result);
		return "success";
	}
	
	@RequestMapping(value = { "org/{id}" }, method = RequestMethod.DELETE)
	public String deleteOrganization(@PathVariable int id) {
		organizationService.deleteOrganizationbyId(id);
		return "success";
	}
	
	@RequestMapping(value={"org/{id}"},method=RequestMethod.POST)
	public String updateOrganization(@PathVariable("id") int id,
			 @RequestParam(value = "name",required=true) String name,
			 @RequestParam(value = "description", defaultValue = "") String description,
			 @RequestParam(value = "state",defaultValue = "") String state,
			 @RequestParam(value= "city",defaultValue = "") String city,
			 @RequestParam(value = "street", defaultValue = "") String street,
			 @RequestParam(value = "zip", defaultValue = "") String zip,
			 ModelMap model)
	{	
		// get the organization with the given id
		Organization org  = organizationService.findById(id);
		if(org==null)
			System.out.println(" no record with this id found ");
		else
		{
			System.out.println(" setting the update records");
			Address address = org.getAddress();
			org.setName(name);
			if(state != null && !state.isEmpty())
				address.setState(state);
			if(city != null && !city.isEmpty())
				address.setCity(city);
			if(zip != null && !zip.isEmpty())
				address.setZip(zip);
			if(street != null && !street.isEmpty())
				address.setStreet(street);
			if(description != null && !description.isEmpty())
				org.setDescription(description);
			
		
		organizationService.updateOrganization(org);
		System.out.println(" ::::::::organization name is :::::"+org.getName());
		}
		return "success";
		
	}
	
	
	private void validateOrganization(int orgId) {
		Organization orgObj = this.organizationService.findById(orgId);
		if(orgObj==null) throw new OrganizationNotFoundException(" organization ");
	}
	
}
