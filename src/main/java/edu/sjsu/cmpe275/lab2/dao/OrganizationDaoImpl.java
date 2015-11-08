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
	{	int id=0;
		Organization objOrg=null;
		Session session = (Session)getSession();
		try{
		session.save(org);
		id=org.getId();
		objOrg = getByKey(id);
		}catch(Exception ex)
		{
			System.err.println(ex);
		}
				
		return objOrg;
	}
	
	public void deleteOrganizationById(int id)
	{	Organization org = new Organization();
		org.setId(id);
		delete(org);
	}
	
	public Organization updateOrganization(Organization org)
	{
		try{
		update(org);
		return org;
		}catch(Exception ex)
		{
			System.err.println(ex);
		}
		return null; 
	}
	
}
