package br.com.locatech.locatech.entities;

import br.com.locatech.locatech.dto.VehicleRequestDTO;
import lombok.*;

import java.math.BigDecimal;

/**
 * Represents the basic info about a car that is available (or not) for renting.
 * <p>This class uses Lombok annotations to automatically generate getters and setters, along with equals,
 * hashCode and toString methods.</p>
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Vehicle {
    private Long id;
    private String brand;
    private String model;
    private String plate;
    private int vehicleYear;
    private String color;
    private BigDecimal dailyPrice;
    private boolean available;

    public Vehicle(VehicleRequestDTO vehicleRequestDTO) {
        this.brand = vehicleRequestDTO.brand();
        this.model = vehicleRequestDTO.model();
        this.plate = vehicleRequestDTO.plate();
        this.vehicleYear = vehicleRequestDTO.vehicleYear();
        this.color = vehicleRequestDTO.color();
        this.dailyPrice = vehicleRequestDTO.dailyPrice();
        this.available = vehicleRequestDTO.available();
    }
}
