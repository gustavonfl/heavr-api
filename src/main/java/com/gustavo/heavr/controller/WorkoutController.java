package com.gustavo.heavr.controller;

import com.gustavo.heavr.dto.request.WorkoutRequest;
import com.gustavo.heavr.dto.response.WorkoutResponse;
import com.gustavo.heavr.entity.User;
import com.gustavo.heavr.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<List<WorkoutResponse>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(workoutService.findAll(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponse> findById(@PathVariable Long id,
                                                    @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(workoutService.findById(id, user));
    }

    @PostMapping
    public ResponseEntity<WorkoutResponse> create(@Valid @RequestBody WorkoutRequest request,
                                                  @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workoutService.create(request, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody WorkoutRequest request,
                                                  @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(workoutService.update(id, request, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @AuthenticationPrincipal User user) {
        workoutService.delete(id, user);
        return ResponseEntity.noContent().build();
    }
}