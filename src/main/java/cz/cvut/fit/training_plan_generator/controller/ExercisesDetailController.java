package cz.cvut.fit.training_plan_generator.controller;

import cz.cvut.fit.training_plan_generator.dao.ExerciseRepository;
import cz.cvut.fit.training_plan_generator.domain.Exercise;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ExercisesDetailController {

    private final ExerciseRepository exerciseRepository;

    public ExercisesDetailController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping(value = "/exercises/detail", produces = "application/hal+json")
    @ResponseStatus(HttpStatus.OK)
    public List<Exercise> getExercisesDetail() {
        List<Exercise> allExercises = (List<Exercise>) this.exerciseRepository.findAll();
        return allExercises;
    }
}
