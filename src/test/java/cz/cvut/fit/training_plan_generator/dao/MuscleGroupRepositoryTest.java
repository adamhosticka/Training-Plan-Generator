package cz.cvut.fit.training_plan_generator.dao;

import cz.cvut.fit.training_plan_generator.TrainingPlanGenerator;
import cz.cvut.fit.training_plan_generator.domain.MuscleGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrainingPlanGenerator.class)
public class MuscleGroupRepositoryTest {
    @Autowired
    private MuscleGroupRepository muscleGroupRepository;

    @AfterEach
    void clearRepository() {
        muscleGroupRepository.deleteAll();
    }

    @Test
    void addMuscleGroup() {
        MuscleGroup tongue = new MuscleGroup("Tongue", 99);
        this.muscleGroupRepository.save(tongue);
        assertEquals(1, muscleGroupRepository.count());
    }

    @Test
    void testMGRepo() {
        assertEquals(0, muscleGroupRepository.count());
    }
}
