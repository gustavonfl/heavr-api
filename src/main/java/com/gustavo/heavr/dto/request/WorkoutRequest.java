package com.gustavo.heavr.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record WorkoutRequest(
        @NotBlank String name,
        String description,
        @Valid List<ExerciseRequest> exercises
) {
}
