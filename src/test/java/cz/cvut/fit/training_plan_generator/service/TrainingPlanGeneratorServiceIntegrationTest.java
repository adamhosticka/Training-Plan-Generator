package cz.cvut.fit.training_plan_generator.service;

import cz.cvut.fit.training_plan_generator.TrainingPlanGenerator;
import cz.cvut.fit.training_plan_generator.controller.model.TrainingPlanDTO;
import cz.cvut.fit.training_plan_generator.dao.ExerciseRepository;
import cz.cvut.fit.training_plan_generator.dao.MuscleGroupRepository;
import cz.cvut.fit.training_plan_generator.dao.TrainingPlanRepository;
import cz.cvut.fit.training_plan_generator.domain.Category;
import cz.cvut.fit.training_plan_generator.domain.Exercise;
import cz.cvut.fit.training_plan_generator.domain.MuscleGroup;
import cz.cvut.fit.training_plan_generator.domain.TrainingPlan;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

import static cz.cvut.fit.training_plan_generator.service.TrainingPlanGeneratorService.exerciseLimit;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrainingPlanGenerator.class)
public class TrainingPlanGeneratorServiceIntegrationTest {
    @Autowired
    private TrainingPlanGeneratorService trainingPlanGeneratorService;
    @MockBean
    private MuscleGroupRepository muscleGroupRepository;
    @MockBean
    private ExerciseRepository exerciseRepository;
    @MockBean
    private TrainingPlanRepository trainingPlanRepository;


    @Test
    public void createCorrectPlanWhenDTOCorrect() {
        TrainingPlanDTO planDTO = new TrainingPlanDTO("Pushday", 22, "Male", 51, "Strength", List.of(1L));

        MuscleGroup back = new MuscleGroup("Back", 3);
        back.setId(1L);
        MuscleGroup chest = new MuscleGroup("Chest", 3);
        chest.setId(2L);

        Mockito.when(this.muscleGroupRepository.findAllById(List.of(1L))).thenReturn(List.of(back));

        Category category = new Category("testCat", 3, 10, 5);
        Exercise benchPress = new Exercise("Bench Press", category, new HashSet<>(List.of(chest)));
        Exercise rows = new Exercise("Rows", category, new HashSet<>(List.of(back)));
        Exercise pullups = new Exercise("Pull Ups", category, new HashSet<>(List.of(back)));

        Mockito.when(this.exerciseRepository.findAll()).thenReturn(List.of(benchPress, rows, pullups));

        Mockito.when(this.trainingPlanRepository.save(new TrainingPlan())).thenReturn(null);

        TrainingPlan plan = this.trainingPlanGeneratorService.createPlan(planDTO);

        assertEquals(planDTO.getName(), plan.getName());
        assertEquals(planDTO.getAge(), plan.getAge());
        assertEquals(planDTO.getGender(), plan.getGender());
        assertEquals(planDTO.getTimeToTrain(), plan.getTimeToTrain());
        assertEquals(new HashSet<>(List.of(back)), plan.getMuscleGroups());
        assertEquals(new HashSet<>(List.of(pullups, rows)), new HashSet<>(plan.getExercises()));
        assertTrue(plan.getExercises().size() <= exerciseLimit);
    }
}
