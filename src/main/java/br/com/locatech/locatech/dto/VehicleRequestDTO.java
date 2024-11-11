package br.com.locatech.locatech.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record VehicleRequestDTO(
        @NotNull(message = "Vehicle brand cannot be null!") String brand,
        @NotNull(message = "Vehicle model cannot be null!") String model,
        @NotNull(message = "Vehicle plate cannot be null!") String plate,
        @NotNull(message = "Vehicle year cannot be null!") int vehicleYear,
        @NotNull(message = "Vehicle color cannot be null!") String color,
        @NotNull(message = "Vehicle daily price cannot be null!") BigDecimal dailyPrice,
        boolean available
) {}
