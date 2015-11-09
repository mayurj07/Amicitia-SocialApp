package edu.sjsu.cmpe275.lab2.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.lab2.model.Organization;

@Repository("organizationDao")
public class OrganizationDaoImpl extends AbstractDao<Integer, Organization> implements OrganizationDao {
	
	public Organization findById(int id) {
		return getByKey(id);
	}
	
	public Organization saveOrganization(Organization org)
	{	
		persist(org);
		
		return org;
	}
	
	public void deleteOrganizationById(int id)
	{	Organization org = new Organization();
		org.setId(id);
		delete(org);
	}
	
	public Organization updateOrganization(Organization org)
	{	
		
		update(org);
		return org;
		 
	}
	
}
