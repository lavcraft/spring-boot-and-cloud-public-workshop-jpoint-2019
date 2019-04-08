package ru.demo.math.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.demo.Exercise;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class MathController {
    @Value("${max}")
    int max;
    private Random random = new Random();

    @GetMapping("/random")
    public List<Exercise> exerciseList(@RequestParam int amount) {

        return Stream.generate(this::randomExercise)
                .limit(amount)
                .collect(Collectors.toList());
    }

    private Exercise randomExercise() {
        int first  = getRandomInt();
        int second = getRandomInt();

        return Exercise.builder()
                .question(first + " + " + second)
                .answer("answer: " + (first + second))
                .build();
    }

    private int getRandomInt() {
        return random.nextInt(max);
    }
}
