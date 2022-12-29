package cz.cvut.fit.training_plan_generator.controller;

import cz.cvut.fit.training_plan_generator.domain.Exercise;
import cz.cvut.fit.training_plan_generator.service.ExercisesDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ExercisesDetailController {
    private final ExercisesDetailService exercisesDetailService;

    public ExercisesDetailController(ExercisesDetailService exercisesDetailService) {
        this.exercisesDetailService = exercisesDetailService;
    }

    @GetMapping(value = "/exercises/detail", produces = "application/hal+json")
    @ResponseStatus(HttpStatus.OK)
    public List<Exercise> getExercisesDetail() {
        return this.exercisesDetailService.getExercisesDetail();
    }
}
