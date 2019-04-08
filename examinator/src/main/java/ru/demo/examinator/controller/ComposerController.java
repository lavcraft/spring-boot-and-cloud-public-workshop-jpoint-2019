package ru.demo.examinator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.demo.Exam;
import ru.demo.Exercise;
import ru.demo.Section;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ComposerController {
    private final RestTemplate restTemplate;

    private Map<String, String> seviceUrlByName = new HashMap<>();

    private DiscoveryClient discoveryClient;

    @PostConstruct
    public void init() {
        seviceUrlByName.put("math", "http://localhost:8081/random");
        seviceUrlByName.put("java", "http://localhost:8082/random");
    }

    @PostMapping("/exam")
    public Exam getExam(@RequestBody Map<String, Integer> sections) {

        return Exam.builder()
                .sections(sections.entrySet().stream()
                        .map(entry -> {
                            String sectionName = entry.getKey();
                            int    amount      = entry.getValue();
                            String serviceUrl  = seviceUrlByName.get(sectionName);
                            Exercise[] forObject = restTemplate.getForObject(
                                    serviceUrl + "?amount=" + amount,
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
