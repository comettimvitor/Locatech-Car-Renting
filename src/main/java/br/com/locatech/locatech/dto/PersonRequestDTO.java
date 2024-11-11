package br.com.locatech.locatech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record PersonRequestDTO(
        @NotNull(message = "Person name cannot be null!") String name,
        @NotNull(message = "Person cpf cannot be null!") String cpf,
        @NotNull(message = "Person telephone cannot be null!") String telephone,
        @Email String email
) {}
