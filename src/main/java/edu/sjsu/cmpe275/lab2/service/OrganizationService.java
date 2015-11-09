package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.model.Organization;

public interface OrganizationService {

	Organization findById(int id);
	
	Organization saveOrganization(Organization org);
	
	Organization updateOrganization(Organization org);
	
	Organization deleteOrganizationbyId(int id) throws Exception;
}
