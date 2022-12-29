package cz.cvut.fit.training_plan_generator.controller;

import cz.cvut.fit.training_plan_generator.controller.model.TrainingPlanDTO;
import cz.cvut.fit.training_plan_generator.domain.TrainingPlan;
import cz.cvut.fit.training_plan_generator.service.TrainingPlanGeneratorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class TrainingPlanGeneratorController {
    private final TrainingPlanGeneratorService trainingPlanGeneratorService;

    public TrainingPlanGeneratorController(TrainingPlanGeneratorService trainingPlanGeneratorService) {
        this.trainingPlanGeneratorService = trainingPlanGeneratorService;
    }

    @PostMapping(value = "/createPlan", produces = "application/hal+json")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingPlan createPlan(@RequestBody TrainingPlanDTO planDto) {
        return this.trainingPlanGeneratorService.createPlan(planDto);
    }
}
