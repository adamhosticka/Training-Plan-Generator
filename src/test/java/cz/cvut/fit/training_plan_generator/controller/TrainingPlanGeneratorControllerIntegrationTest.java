package cz.cvut.fit.training_plan_generator.controller;

import cz.cvut.fit.training_plan_generator.TrainingPlanGenerator;
import cz.cvut.fit.training_plan_generator.controller.model.TrainingPlanDTO;
import cz.cvut.fit.training_plan_generator.domain.MuscleGroup;
import cz.cvut.fit.training_plan_generator.domain.TrainingPlan;
import cz.cvut.fit.training_plan_generator.service.TrainingPlanGeneratorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrainingPlanGenerator.class)
public class TrainingPlanGeneratorControllerIntegrationTest {
    @Autowired
    private TrainingPlanGeneratorController trainingPlanGeneratorController;
    @MockBean
    private TrainingPlanGeneratorService trainingPlanGeneratorService;


    @Test
    void callsTrainingPlanGeneratorService() {
        TrainingPlanDTO planDTOWrong = new TrainingPlanDTO("", 18, "Male", 44, "Strength", List.of());
        TrainingPlanDTO planDTOCorrect = new TrainingPlanDTO("PlanName", 18, "Male", 44, "Strength", List.of(1L, 2L));
        MuscleGroup tongue = new MuscleGroup("Tongue", 99);
        TrainingPlan planCorrect = new TrainingPlan("PlanName", 18, "Male", 44, "Strength", new HashSet<>(List.of(tongue)));

        Mockito.when(this.trainingPlanGeneratorService.createPlan(planDTOWrong)).thenThrow(ConstraintViolationException.class);
        Mockito.when(this.trainingPlanGeneratorService.createPlan(planDTOCorrect)).thenReturn(planCorrect);

        assertThrows(ConstraintViolationException.class, () -> this.trainingPlanGeneratorController.createPlan(planDTOWrong));
        assertEquals(planCorrect, this.trainingPlanGeneratorController.createPlan(planDTOCorrect));
    }
}
