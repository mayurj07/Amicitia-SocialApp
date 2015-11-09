package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.exception.EntityNotFound;
import edu.sjsu.cmpe275.lab2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}


