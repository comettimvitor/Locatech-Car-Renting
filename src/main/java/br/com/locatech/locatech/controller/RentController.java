package br.com.locatech.locatech.controller;

import br.com.locatech.locatech.dto.RentRequestDTO;
import br.com.locatech.locatech.entities.Person;
import br.com.locatech.locatech.entities.Rent;
import br.com.locatech.locatech.services.PersonService;
import br.com.locatech.locatech.services.RentService;
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
@RequestMapping("/rent")
@Tag(name = "Renting", description = "Controller for the renting CRUD.")
public class RentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentController.class);

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @Operation(
            description = "Searches for all the renting registers.",
            summary = "All renting registers search.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<Rent>> findAllRents(@RequestParam("page") int page, @RequestParam("size") int size) {
        LOGGER.info("/rent");
        var rent = this.rentService.findAllRents(page, size);
        return ResponseEntity.ok(rent);
    }

    @Operation(
            description = "Searches for a single renting register by it's ID.",
            summary = "Renting register search by ID.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Rent>> findRent(@PathVariable("id") Long id) {
        LOGGER.info("/rent/" + id);

        var rent = this.rentService.findRentById(id);

        return ResponseEntity.ok(rent);
    }

    @Operation(
            description = "Creates a new renting register in the database.",
            summary = "Creates a renting register.",
            responses = {
                    @ApiResponse(description = "CREATED", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveRent(@Valid @RequestBody RentRequestDTO rent) {
        LOGGER.info("POST => /rent");

        this.rentService.saveRent(rent);

        return ResponseEntity.status(201).build();
    }

    @Operation(
            description = "Updates a single renting register by it's ID.",
            summary = "Updates a renting register by it's ID.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRent(@PathVariable("id") Long id, @RequestBody RentRequestDTO rent) {
        LOGGER.info("PUT => /rent/" + id);

        this.rentService.updateRent(rent, id);

        return ResponseEntity.ok().build();
    }

    @Operation(
            description = "Deletes a single renting register by it's ID.",
            summary = "Deletes a renting register by it's ID.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable("id") Long id) {
        LOGGER.info("DELETE => /rent/" + id);

        this.rentService.deleteRent(id);

        return ResponseEntity.ok().build();
    }
}
