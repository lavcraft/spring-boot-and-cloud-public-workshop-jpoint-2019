package ru.demo;


import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    private String title;

    @Singular
    private List<Section> sections;
}
