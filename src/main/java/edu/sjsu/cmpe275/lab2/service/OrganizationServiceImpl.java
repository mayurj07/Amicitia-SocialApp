package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.dao.PersonDAO;
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

    @Autowired
    private PersonDAO personDAO;

	/**
	 * Get Organization object based on ID
	 *
	 * @param id
	 * @return
     */
	public Organization findById(int id) {
		return organizationDao.findById(id);
	}

	/**
	 * Save Organization Object
	 *
	 * @param org
	 * @return
     */
	public Organization saveOrganization(Organization org)
	{	
		return organizationDao.saveOrganization(org);
	}

	/**
	 * Update Organization Object
	 *
	 * @param orgn
	 * @return
     */
	public Organization updateOrganization(Organization orgn) {
		Organization orgRet = organizationDao.updateOrganization(orgn);
		return orgRet;
	}

	/**
	 * Delete Organization object
	 *
	 * @param id
	 * @return
	 * @throws Exception
     */
	public Organization deleteOrganizationbyId(int id) throws Exception
	{
        Boolean personExists = personDAO.organizationExists(id);
        System.out.println(personExists);
        if(personExists){
            throw new Exception("Cannot delete Org as person still exists in Org ");
        }
        else {
            Organization organization = organizationDao.deleteOrganizationById(id);
            return organization;
        }
	}
}
