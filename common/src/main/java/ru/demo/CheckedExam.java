package ru.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckedExam {

    @Delegate
    @JsonIgnore
    private Exam exam = new Exam();

}
