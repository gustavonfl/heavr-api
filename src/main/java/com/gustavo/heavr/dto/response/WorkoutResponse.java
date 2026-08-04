package com.gustavo.heavr.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record WorkoutResponse(
        Long id,
        String name,
        String description,
        List<ExerciseResponse> exercises,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
