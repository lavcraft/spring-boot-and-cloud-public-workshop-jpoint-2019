package ru.demo.examinator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.demo.Exam;
import ru.demo.Exercise;
import ru.demo.Section;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RefreshScope
public class ComposerController {
    @Value("${foo.bar}")
    String a;

    @GetMapping("/a")
    public String aaa() {
        return a;
    }
    @Autowired
    @LoadBalanced
    private  RestTemplate restTemplate;

    @Autowired
    private RestTemplate restTemplateWithoutMagic;

    @PostMapping("/exam")
    public Exam getExam(@RequestBody Map<String, Integer> sections) {

        return Exam.builder()
                .sections(sections.entrySet().stream()
                        .map(entry -> {
                            String sectionName = entry.getKey();
                            int    amount      = entry.getValue();

                            Exercise[] forObject = restTemplate.getForObject(
                                    "http://"+sectionName + "/random?amount=" + amount,
                                    Exercise[].class
                            );

                            return Section.builder()
                                    .exercises(Arrays.asList(forObject))
                                    .build();
                        })
                        .collect(Collectors.toList()))
                .build();
    }
}
