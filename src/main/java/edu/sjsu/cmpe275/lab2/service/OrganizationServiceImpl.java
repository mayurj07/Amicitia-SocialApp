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
		Organization entity = organizationDao.findById(orgn.getId());
		Address address  =new Address();
		if(orgn.getAddress()!=null)
		{
			if(orgn.getAddress().getCity()!=null||orgn.getAddress().getCity().length()!=0)
				address.setCity(orgn.getAddress().getCity());
			if(orgn.getAddress().getState()!=null||orgn.getAddress().getState().length()!=0)
				address.setState(orgn.getAddress().getState());
			if(orgn.getAddress().getStreet()!=null||orgn.getAddress().getStreet().length()!=0)
				address.setStreet(orgn.getAddress().getStreet());
			if(orgn.getAddress().getZip()!=null||orgn.getAddress().getZip().length()!=0)
				address.setZip(orgn.getAddress().getZip());
		}
		
		if(entity!=null){
			if(orgn.getName()==null||orgn.getName().length()==0)
				entity.setName(orgn.getName());
			entity.setDescription(orgn.getDescription());
			entity.setAddress(address);
		}
		Organization orgRet = organizationDao.updateOrganization(entity);
		return orgRet;
	}
	
	public void deleteOrganizationbyId(int id)
	{
		organizationDao.deleteOrganizationById(id);
	}
}
