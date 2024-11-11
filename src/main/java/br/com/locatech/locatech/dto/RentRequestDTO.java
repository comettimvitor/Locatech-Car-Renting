package br.com.locatech.locatech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RentRequestDTO(
        @Schema(description = "ID from the person that is renting the vehicle.")
        @NotNull(message = "Person ID cannot be null!") Long personId,
        @NotNull(message = "Vehicle ID cannot be null!") Long vehicleId,
        LocalDate beginDate,
        LocalDate endDate
) {}
