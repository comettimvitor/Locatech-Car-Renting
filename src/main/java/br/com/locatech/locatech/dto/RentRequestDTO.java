package br.com.locatech.locatech.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RentRequestDTO(
        @NotNull(message = "Person ID cannot be null!") Long personId,
        @NotNull(message = "Vehicle ID cannot be null!") Long vehicleId,
        LocalDate beginDate,
        LocalDate endDate) {}
