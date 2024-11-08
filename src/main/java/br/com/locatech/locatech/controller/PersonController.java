package br.com.locatech.locatech.controller;

import br.com.locatech.locatech.entities.Person;
import br.com.locatech.locatech.entities.Vehicle;
import br.com.locatech.locatech.services.PersonService;
import br.com.locatech.locatech.services.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAllPeople(@RequestParam("page") int page, @RequestParam("size") int size) {
        LOGGER.info("/person");
        var person = this.personService.findAllPeople(page, size);
        return ResponseEntity.ok(person);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> findPerson(@PathVariable("id") Long id) {
        LOGGER.info("/person/" + id);

        var person = this.personService.findPersonById(id);

        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Void> savePerson(@RequestBody Person person) {
        LOGGER.info("POST => /person");

        this.personService.savePerson(person);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
        LOGGER.info("PUT => /person/" + id);

        this.personService.updatePerson(person, id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Long id) {
        LOGGER.info("DELETE => /person/" + id);

        this.personService.deletePerson(id);

        return ResponseEntity.ok().build();
    }
}