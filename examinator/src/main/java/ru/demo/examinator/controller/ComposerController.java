package ru.demo.examinator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.demo.Exam;
import ru.demo.Exercise;
import ru.demo.Section;

@RestController
public class ComposerController {

    @PostMapping("/exam")
    public Exam getExam() {
        return Exam.builder()
                .section(Section.builder()
                        .exercise(Exercise.builder()
                                .answer("")
                                .question("f")
                                .build())
                        .build())
                .build();
    }
}
