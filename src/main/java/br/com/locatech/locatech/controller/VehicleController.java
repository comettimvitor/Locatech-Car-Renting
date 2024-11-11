package br.com.locatech.locatech.controller;

import br.com.locatech.locatech.dto.VehicleRequestDTO;
import br.com.locatech.locatech.entities.Vehicle;
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
@RequestMapping("/vehicles")
@Tag(name = "Vehicles", description = "Controller for the vehicles CRUD.")
public class VehicleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    //    http://localhost:8080/vehicles?page=1&size=10
    @Operation(
            description = "Searches for all vehicles in the database.",
            summary = "All vehicles search.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<Vehicle>> findAllVehicles(@RequestParam("page") int page, @RequestParam("size") int size) {
        LOGGER.info("/vehicles");
        var vehicles = this.vehicleService.findAllVehicles(page, size);
        return ResponseEntity.ok(vehicles);
    }

    //    http://localhost:8080/vehicles/1
    @Operation(
            description = "Searches for a vehicle in the database by it's ID.",
            summary = "Vehicles search by ID .",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vehicle>> findVehicle(@PathVariable("id") Long id) {
        LOGGER.info("/vehicles/" + id);

        var vehicle = this.vehicleService.findVehicleById(id);

        return ResponseEntity.ok(vehicle);
    }

    @Operation(
            description = "Create a new Vehicle in the database.",
            summary = "Create vehicle.",
            responses = {
                    @ApiResponse(description = "CREATED", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveVehicle(@Valid @RequestBody VehicleRequestDTO vehicle) {
        LOGGER.info("POST => /vehicles");

        this.vehicleService.saveVehicle(vehicle);

        return ResponseEntity.status(201).build();
    }

    @Operation(
            description = "Update a vehicle according to the selected ID.",
            summary = "Update a vehicle by its ID.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVehicle(@PathVariable("id") Long id, @RequestBody VehicleRequestDTO vehicle) {
        LOGGER.info("PUT => /vehicles/" + id);

        this.vehicleService.updateVehicle(vehicle, id);

        return ResponseEntity.ok().build();
    }

    @Operation(
            description = "Delete a vehicle according to the selected ID.",
            summary = "Delete a vehicle by its ID.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("id") Long id) {
        LOGGER.info("DELETE => /vehicles/" + id);

        this.vehicleService.deleteVehicle(id);

        return ResponseEntity.ok().build();
    }
}
