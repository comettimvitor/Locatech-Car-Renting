package br.com.locatech.locatech.controller;

import br.com.locatech.locatech.dto.PersonRequestDTO;
import br.com.locatech.locatech.entities.Person;
import br.com.locatech.locatech.entities.Vehicle;
import br.com.locatech.locatech.services.PersonService;
import br.com.locatech.locatech.services.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@Tag(name = "People", description = "Controller for the people (clients) CRUD.")
public class PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(
            description = "Searches for all clients in the database.",
            summary = "All clients search.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<Person>> findAllPeople(@RequestParam("page") int page, @RequestParam("size") int size) {
        LOGGER.info("/person");
        var person = this.personService.findAllPeople(page, size);
        return ResponseEntity.ok(person);
    }

    @Operation(
            description = "Searches for a single person by it's ID.",
            summary = "Clients by ID search.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> findPerson(@PathVariable("id") Long id) {
        LOGGER.info("/person/" + id);

        var person = this.personService.findPersonById(id);

        return ResponseEntity.ok(person);
    }

    @Operation(
            description = "Create a new client in the database.",
            summary = "Creates a client.",
            responses = {
                    @ApiResponse(description = "CREATED", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Void> savePerson(@Valid @RequestBody PersonRequestDTO person) {
        LOGGER.info("POST => /person");

        this.personService.savePerson(person);

        return ResponseEntity.status(201).build();
    }

    @Operation(
            description = "Updates a single person's register by it's ID.",
            summary = "Updates a person by it's ID.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable("id") Long id, @RequestBody PersonRequestDTO person) {
        LOGGER.info("PUT => /person/" + id);

        this.personService.updatePerson(person, id);

        return ResponseEntity.ok().build();
    }

    @Operation(
            description = "Deletes a single person's register by it's ID.",
            summary = "Deletes a person by it's ID.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Long id) {
        LOGGER.info("DELETE => /person/" + id);

        this.personService.deletePerson(id);

        return ResponseEntity.ok().build();
    }
}