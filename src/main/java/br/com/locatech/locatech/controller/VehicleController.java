package br.com.locatech.locatech.controller;

import br.com.locatech.locatech.entities.Vehicle;
import br.com.locatech.locatech.services.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

//    http://localhost:8080/vehicles?page=1&size=10
    @GetMapping
    public ResponseEntity<List<Vehicle>> findAllVehicles(@RequestParam("page") int page, @RequestParam("size") int size) {
        LOGGER.info("/vehicles");
        var vehicles = this.vehicleService.findAllVehicles(page, size);
        return ResponseEntity.ok(vehicles);
    }

//    http://localhost:8080/vehicles/1
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vehicle>> findVehicle(@PathVariable("id") Long id) {
        LOGGER.info("/vehicles/" + id);

        var vehicle = this.vehicleService.findVehicleById(id);

        return ResponseEntity.ok(vehicle);
    }

    @PostMapping
    public ResponseEntity<Void> saveVehicle(@RequestBody Vehicle vehicle) {
        LOGGER.info("POST => /vehicles");

        this.vehicleService.saveVehicle(vehicle);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVehicle(@PathVariable("id") Long id, @RequestBody Vehicle vehicle) {
        LOGGER.info("PUT => /vehicles/" + id);

        this.vehicleService.updateVehicle(vehicle, id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("id") Long id) {
        LOGGER.info("DELETE => /vehicles/" + id);

        this.vehicleService.deleteVehicle(id);

        return ResponseEntity.ok().build();
    }
}
