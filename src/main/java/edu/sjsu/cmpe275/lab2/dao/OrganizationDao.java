package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.model.Organization;

public interface OrganizationDao {
	
	Organization findById(int id);
	
	Organization saveOrganization(Organization org);
	
	Organization deleteOrganizationById(int id);
	
	Organization updateOrganization(Organization org);

}
