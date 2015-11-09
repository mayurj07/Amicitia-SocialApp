package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.dao.PersonDAO;
import edu.sjsu.cmpe275.lab2.exception.EntityNotFound;
import edu.sjsu.cmpe275.lab2.model.Address;
import edu.sjsu.cmpe275.lab2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("PersonService")
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDao;

    public Person getPersonInfo(int personId) {
        return personDao.getPersonInfo(personId);
    }

    public Person createPerson(Person person) {
        return personDao.createPerson(person);
    }

    public List<Person> getAllPerson() {
        return null;
    }

    public Person updatePersonInfo(Person updatedPerson) throws EntityNotFound {
        //Person entity = personDao.getPersonInfo((int) updatedPerson.getId());
        /*Address address = new Address();

        if (updatedPerson.getAddress() != null) {
            if (updatedPerson.getAddress().getCity() != null || updatedPerson.getAddress().getCity().length() != 0)
                address.setCity(updatedPerson.getAddress().getCity());
            if (updatedPerson.getAddress().getState() != null || updatedPerson.getAddress().getState().length() != 0)
                address.setState(updatedPerson.getAddress().getState());
            if (updatedPerson.getAddress().getStreet() != null || updatedPerson.getAddress().getStreet().length() != 0)
                address.setStreet(updatedPerson.getAddress().getStreet());
            if (updatedPerson.getAddress().getZip() != null || updatedPerson.getAddress().getZip().length() != 0)
                address.setZip(updatedPerson.getAddress().getZip());
        }

        if (entity != null) {
            if (updatedPerson.getFirstname() == null || updatedPerson.getFirstname().length() == 0)
                entity.setFirstname(updatedPerson.getFirstname());
            entity.setDescription(updatedPerson.getDescription());
            entity.setAddress(address);
        }*/
        Person perRet = personDao.updatePersonInfo(updatedPerson);
        return perRet;
    }

    public Person deletePerson(int id) {
        Person person = personDao.deletePerson(id);
        return person;

    }

}

