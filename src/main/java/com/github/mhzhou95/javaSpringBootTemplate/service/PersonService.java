package com.github.mhzhou95.javaSpringBootTemplate.service;

import com.github.mhzhou95.javaSpringBootTemplate.model.Person;
import com.github.mhzhou95.javaSpringBootTemplate.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.get();
    }
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person delete(Long id) {
        Optional<Person> person = personRepository.findById(id);
        personRepository.deleteById(id);
        return person.get();
    }

    public Person editPerson(Long id, Person person) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        Person personBefore = optionalPerson.get();
        if(optionalPerson.isPresent()){
            if(person.getFirstName() != null){
                personBefore.setFirstName(person.getFirstName());
            }
            if(person.getLastName() != null){
                personBefore.setLastName(person.getLastName());
            }
            return personRepository.save(personBefore);
        }
        return null;
    }
}
