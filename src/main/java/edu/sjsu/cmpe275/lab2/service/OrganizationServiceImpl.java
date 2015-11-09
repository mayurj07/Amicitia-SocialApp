package edu.sjsu.cmpe275.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.lab2.dao.OrganizationDao;
import edu.sjsu.cmpe275.lab2.model.Address;
import edu.sjsu.cmpe275.lab2.model.Organization;

@Service("organizationService")
@Transactional
public class OrganizationServiceImpl implements OrganizationService{
	
	@Autowired
	private OrganizationDao organizationDao;
	
	public Organization findById(int id) {
		// TODO Auto-generated method stub
		System.out.println(" ::: in serviec class:::"+id);
		
		return organizationDao.findById(id);
	}
	
	public Organization saveOrganization(Organization org)
	{	
		System.out.println("::: in the save organization:::");
		return organizationDao.saveOrganization(org);
	}
	
	public Organization updateOrganization(Organization orgn) {
		
		Organization orgRet = organizationDao.updateOrganization(orgn);
		return orgRet;
	}
	
	public void deleteOrganizationbyId(int id)
	{
		organizationDao.deleteOrganizationById(id);
	}
}
