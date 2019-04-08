package ru.demo.math.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.demo.Exercise;
import ru.demo.math.repository.ExerciseDTO;
import ru.demo.math.repository.JavaExercisesRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class JavaController {
    @PostConstruct
    public void init() {
        javaExercisesRepository.save(ExerciseDTO.builder()
                .question("Последняя версия java")
                .answer("12")
                .build());
        javaExercisesRepository.save(ExerciseDTO.builder()
                .question("Последняя следующая версия джава")
                .answer("13")
                .build());
    }
    private final JavaExercisesRepository javaExercisesRepository;

    @GetMapping("/random")
    public List<ExerciseDTO> exerciseList(@RequestParam int amount) {

        List<ExerciseDTO> collect = new ArrayList<>(javaExercisesRepository.findAll());

        Collections.shuffle(collect);

        return collect.subList(0, amount);
    }

}
