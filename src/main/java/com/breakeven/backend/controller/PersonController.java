package com.breakeven.backend.controller;

import com.breakeven.backend.dataTransferObject.request.PersonCreateRequest;
import com.breakeven.backend.error.CustomResponse;
import com.breakeven.backend.model.Initiative;
import com.breakeven.backend.service.InitiativeService;
import com.breakeven.backend.service.PersonService;
import com.breakeven.backend.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;
    private final InitiativeService initiativeService;

    @Autowired
    public PersonController(PersonService personService, InitiativeService initiativeService) {
        this.personService = personService;
        this.initiativeService = initiativeService;
    }

    @GetMapping("/all")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable UUID id) {
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<?> getPersonsByName(@RequestParam String name) {
        try {
            List<Person> persons = personService.getPersonsByName(name);
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            CustomResponse customResponse = new CustomResponse(
                    CustomResponse.ResponseStatus.Fail,
                    "Search Person",
                    "Error occurred while searching for persons: " + e.getMessage()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonCreateRequest request) {
        Optional<Initiative> initiativeOpt = initiativeService.getInitiativeById(request.getInitiativeId());

        if (initiativeOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Initiative not found.");
        }

        Person person = new Person(initiativeOpt.get(), request.getName());
        Optional<Person> newPerson = personService.addNewPerson(person);

        if (newPerson.isEmpty()) {
            CustomResponse customResponse = new CustomResponse(
                CustomResponse.ResponseStatus.Fail,
                "Add Person",
                "Person already exists."
            );
            return ResponseEntity.status(HttpStatus.OK).body(customResponse);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable UUID id) {
        try {
            personService.deletePerson(id);
            CustomResponse customResponse = new CustomResponse(
                    CustomResponse.ResponseStatus.Success,
                    "Delete Person",
                    "Deleted person record."
            );
            return ResponseEntity.status(HttpStatus.OK).body(customResponse);
        } catch (Exception e) {
            CustomResponse customResponse = new CustomResponse(
                    CustomResponse.ResponseStatus.Fail,
                    "Delete Person",
                    "Error occurred while deleting person: " + e.getMessage()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }
    }
}
