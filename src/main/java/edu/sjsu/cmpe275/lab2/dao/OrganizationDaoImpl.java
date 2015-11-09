package edu.sjsu.cmpe275.lab2.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.lab2.model.Organization;

@Repository("organizationDao")
public class OrganizationDaoImpl extends AbstractDao<Integer, Organization> implements OrganizationDao {

	/**
	 * Get organization data from database based on ID
	 * @param id
	 * @return
     */
	public Organization findById(int id) {
		return getByKey(id);
	}

	/**
	 * Save new organization data in database
	 * @param org
	 * @return
     */
	public Organization saveOrganization(Organization org)
	{	
		persist(org);
		return org;
	}

	/**
	 * Delete organization data from database
	 * @param id
	 * @return
     */
	public Organization deleteOrganizationById(int id)
	{
		Organization org = findById(id);
		delete(org);
        return org;
	}

	/**
	 * Update Organization data in database
	 * @param org
	 * @return
     */
	public Organization updateOrganization(Organization org)
	{
		update(org);
		return org;
	}
	
}
