package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.exception.EntityNotFound;
import edu.sjsu.cmpe275.lab2.model.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Queue;

@Repository("PersonDAO")

public class PersonDAOImpl extends AbstractDao<Integer, Person> implements PersonDAO {


    public Person createPerson(Person person) {

        persist(person);
        return person;
    }

    public Person getPersonInfo(int personId) throws EntityNotFound {
        return getByKey(personId);
    }

    public List<Person> getAllPerson() {
        return null;
    }

    public Person updatePersonInfo(Person updatedPerson) throws EntityNotFound {
        update(updatedPerson);
        return updatedPerson;
    }


    public Person deletePerson(int personId) throws EntityNotFound
    {
        Person personToDelete = getByKey(personId);

        if(personToDelete != null){
            delete(personToDelete);
            return personToDelete;
        }
        else {
            throw new EntityNotFound("Person not found.");
        }
    }

    public Boolean organizationExists(int orgID){

        Session s = getSession();
        Query query= s.createSQLQuery("SELECT orgid FROM cmpelab2.person WHERE orgid = " + orgID);
        List result = query.list();

        for (Object aResult : result) {
            System.out.println(aResult);
        }

        if(result.size() == 0){
            return false;
        }else
            return true;
    }

}


