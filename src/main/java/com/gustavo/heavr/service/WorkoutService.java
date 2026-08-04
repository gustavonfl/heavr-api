package com.gustavo.heavr.service;

import com.gustavo.heavr.dto.request.WorkoutRequest;
import com.gustavo.heavr.dto.response.ExerciseResponse;
import com.gustavo.heavr.dto.response.WorkoutResponse;
import com.gustavo.heavr.entity.Exercise;
import com.gustavo.heavr.entity.User;
import com.gustavo.heavr.entity.Workout;
import com.gustavo.heavr.exception.BusinessException;
import com.gustavo.heavr.repository.WorkoutRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    public List<WorkoutResponse> findAll(User user) {
        return workoutRepository.findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public WorkoutResponse findById(Long id, User user) {
        Workout workout = workoutRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new BusinessException("Treino não encontrado."));
        return toResponse(workout);
    }

    @Transactional
    public WorkoutResponse create(WorkoutRequest request, User user) {
        Workout workout = Workout.builder()
                .name(request.name())
                .description(request.description())
                .user(user)
                .build();

        if (request.exercises() != null) {
            request.exercises().forEach(exerciseRequest -> {
                Exercise exercise = Exercise.builder()
                        .name(exerciseRequest.name())
                        .notes(exerciseRequest.notes())
                        .workout(workout)
                        .build();
                workout.getExercise().add(exercise);
            });
        }

        return toResponse(workoutRepository.save(workout));
    }

    @Transactional
    public WorkoutResponse update(Long id, WorkoutRequest request, User user) {
        Workout workout = workoutRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new BusinessException("Treino não encontrado."));

        workout.setName(request.name());
        workout.setDescription(request.description());

        workout.getExercise().clear();

        if (request.exercises() != null) {
            request.exercises().forEach(exerciseRequest -> {
                Exercise exercise = Exercise.builder()
                        .name(exerciseRequest.name())
                        .notes(exerciseRequest.notes())
                        .workout(workout)
                        .build();
                workout.getExercise().add(exercise);
            });
        }

        return toResponse(workoutRepository.save(workout));
    }

    @Transactional
    public void delete(Long id, User user) {
        Workout workout = workoutRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new BusinessException("Treino não encontrado."));
        workoutRepository.delete(workout);
    }

    private WorkoutResponse toResponse(Workout workout) {
        List<ExerciseResponse> exercises = workout.getExercise()
                .stream()
                .map(e -> new ExerciseResponse(e.getId(), e.getName(), e.getNotes()))
                .toList();

        return new WorkoutResponse(
                workout.getId(),
                workout.getName(),
                workout.getDescription(),
                exercises,
                workout.getCreatedAt(),
                workout.getUpdatedAt()
        );
    }
}