package cz.cvut.fit.training_plan_generator.controller;

import cz.cvut.fit.training_plan_generator.dao.ExerciseRepository;
import cz.cvut.fit.training_plan_generator.dao.MuscleGroupRepository;
import cz.cvut.fit.training_plan_generator.dao.TrainingPlanRepository;
import cz.cvut.fit.training_plan_generator.domain.TrainingPlan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:3000")
public class TrainingPlanGeneratorController {
    MuscleGroupRepository muscleGroupRepository;
    ExerciseRepository exerciseRepository;
    TrainingPlanRepository trainingPlanRepository;


    public TrainingPlanGeneratorController(MuscleGroupRepository muscleGroupRepository, ExerciseRepository exerciseRepository, TrainingPlanRepository trainingPlanRepository) {
        this.muscleGroupRepository = muscleGroupRepository;
        this.exerciseRepository = exerciseRepository;
        this.trainingPlanRepository = trainingPlanRepository;
    }

    @PostMapping(value = "/createPlan", produces = "application/hal+json")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingPlan createPlan(@RequestBody TrainingPlan plan) {
        System.out.println("PostPlan" + plan);
        TrainingPlan newPlan = this.trainingPlanRepository.save(plan);
        System.out.println("NewPlan" + newPlan);
        return newPlan;
    }
}
