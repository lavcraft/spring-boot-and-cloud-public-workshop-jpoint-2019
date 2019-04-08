package ru.demo.math.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JavaExercisesRepository extends JpaRepository<ExerciseDTO, Long> {
    ExerciseDTO findAllByAnswer(String answer);
}
