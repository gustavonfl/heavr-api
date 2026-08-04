package com.gustavo.heavr.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ExerciseRequest(
        Long id,
        @NotBlank String name,
        String notes
) {}
