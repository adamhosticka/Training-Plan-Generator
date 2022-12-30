package cz.cvut.fit.training_plan_generator.dao;

import cz.cvut.fit.training_plan_generator.TrainingPlanGenerator;
import cz.cvut.fit.training_plan_generator.domain.Category;
import cz.cvut.fit.training_plan_generator.domain.Exercise;
import cz.cvut.fit.training_plan_generator.domain.MuscleGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrainingPlanGenerator.class)
public class ExerciseRepositoryTest {
    @Autowired
    private MuscleGroupRepository muscleGroupRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;

    @AfterEach
    void clearRepository() {
        exerciseRepository.deleteAll();
        muscleGroupRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void muscleGroupExistsByIdFindById() {
        MuscleGroup legs = this.muscleGroupRepository.save(new MuscleGroup("Legs", 3));
        MuscleGroup back = this.muscleGroupRepository.save(new MuscleGroup("Back", 3));
        MuscleGroup biceps = this.muscleGroupRepository.save(new MuscleGroup("Biceps", 1));
        Category bodyweight = this.categoryRepository.save(new Category("Bodyweight", 1, 2, 20));
        Category machine = this.categoryRepository.save(new Category("Machine", 2, 3, 12));
        Exercise gluteBridge = this.exerciseRepository.save(new Exercise("Glute Bridge", bodyweight, new HashSet<>(List.of(legs))));
        Exercise legSpreadMachine = this.exerciseRepository.save(new Exercise("Leg Spread Machine", machine, new HashSet<>(List.of(legs))));
        Exercise pullups = this.exerciseRepository.save(new Exercise("Pull Ups", bodyweight, new HashSet<>(List.of(back, biceps))));

        assertEquals(
                List.of(gluteBridge, legSpreadMachine, pullups),
                this.exerciseRepository.findAllById(List.of(gluteBridge.getId(), legSpreadMachine.getId(), pullups.getId()))
        );
    }
}
