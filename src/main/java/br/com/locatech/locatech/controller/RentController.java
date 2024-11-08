package br.com.locatech.locatech.controller;

import br.com.locatech.locatech.dto.RentRequestDTO;
import br.com.locatech.locatech.entities.Person;
import br.com.locatech.locatech.entities.Rent;
import br.com.locatech.locatech.services.PersonService;
import br.com.locatech.locatech.services.RentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rent")
public class RentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentController.class);

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping
    public ResponseEntity<List<Rent>> findAllRents(@RequestParam("page") int page, @RequestParam("size") int size) {
        LOGGER.info("/rent");
        var rent = this.rentService.findAllRents(page, size);
        return ResponseEntity.ok(rent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Rent>> findRent(@PathVariable("id") Long id) {
        LOGGER.info("/rent/" + id);

        var rent = this.rentService.findRentById(id);

        return ResponseEntity.ok(rent);
    }

    @PostMapping
    public ResponseEntity<Void> saveRent(@Valid @RequestBody RentRequestDTO rent) {
        LOGGER.info("POST => /rent");

        this.rentService.saveRent(rent);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRent(@PathVariable("id") Long id, @RequestBody Rent rent) {
        LOGGER.info("PUT => /rent/" + id);

        this.rentService.updateRent(rent, id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable("id") Long id) {
        LOGGER.info("DELETE => /rent/" + id);

        this.rentService.deleteRent(id);

        return ResponseEntity.ok().build();
    }
}
