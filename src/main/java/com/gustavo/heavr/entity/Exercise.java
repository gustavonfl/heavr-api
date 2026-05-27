package com.gustavo.heavr.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "exercises")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;
}