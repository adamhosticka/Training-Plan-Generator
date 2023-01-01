package cz.cvut.fit.training_plan_generator.dao;


import cz.cvut.fit.training_plan_generator.TrainingPlanGenerator;
import cz.cvut.fit.training_plan_generator.domain.MuscleGroup;
import cz.cvut.fit.training_plan_generator.domain.TrainingPlan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrainingPlanGenerator.class)
public class TrainingPlanRepositoryTest {
    @Autowired
    private TrainingPlanRepository trainingPlanRepository;
    @Autowired
    private MuscleGroupRepository muscleGroupRepository;

    @AfterEach
    public void clearRepositories() {
        this.trainingPlanRepository.deleteAll();
        this.muscleGroupRepository.deleteAll();
    }

    @Test
    public void throwExceptionWhenBlankName() {
        MuscleGroup chest = this.muscleGroupRepository.save(new MuscleGroup("Chest", 3));
        TrainingPlan plan = new TrainingPlan("", 18, "Male", 55, "Hypertrophy", new HashSet<>(List.of(chest)));

        assertThrows(Exception.class, () -> this.trainingPlanRepository.save(plan));
    }

    @Test
    public void throwExceptionWhenEmptyMuscleGroups() {
        TrainingPlan plan = new TrainingPlan("Plan123", 18, "Male", 55, "Hypertrophy", new HashSet<>(List.of()));

        assertThrows(Exception.class, () -> this.trainingPlanRepository.save(plan));
    }

    @Test
    public void noExceptionThrownIfPlanValid() {
        MuscleGroup chest = this.muscleGroupRepository.save(new MuscleGroup("Chest", 3));
        TrainingPlan plan = new TrainingPlan("Plan123", 18, "Male", 55, "Hypertrophy", new HashSet<>(List.of(chest)));

        this.trainingPlanRepository.save(plan);
    }
}
