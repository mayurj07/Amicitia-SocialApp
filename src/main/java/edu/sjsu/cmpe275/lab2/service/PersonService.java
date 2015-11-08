package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.exception.EntityNotFound;
import edu.sjsu.cmpe275.lab2.model.Person;

import java.util.List;

public interface PersonService {

        public Person createPerson(Person person);

        public Person getPersonInfo(int personId) throws EntityNotFound;

        public List<Person> getAllPerson();

        public Person updatePersonInfo(Person person) throws EntityNotFound;

        public Person deletePerson(int personId) throws EntityNotFound;
    }

