package com.breakeven.backend.service;

import com.breakeven.backend.dataAccessObject.PersonRepository;
import com.breakeven.backend.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personRepository.findById(id);
    }

    public List<Person> getPersonsByName(String name) {
        return personRepository.findByNameContaining(name);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(UUID id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> addNewPerson(Person person) {
        Optional<Person> existingPerson = personRepository.findByName(person.getName());
        if (existingPerson.isEmpty()) {
            return Optional.of(personRepository.save(person));
        }

        return Optional.empty();
    }
}
