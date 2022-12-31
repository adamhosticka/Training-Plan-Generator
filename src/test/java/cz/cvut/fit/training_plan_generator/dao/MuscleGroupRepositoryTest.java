package cz.cvut.fit.training_plan_generator.dao;

import cz.cvut.fit.training_plan_generator.TrainingPlanGenerator;
import cz.cvut.fit.training_plan_generator.domain.MuscleGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrainingPlanGenerator.class)
public class MuscleGroupRepositoryTest {
    @Autowired
    private MuscleGroupRepository muscleGroupRepository;

    @AfterEach
    public void clearRepository() {
        muscleGroupRepository.deleteAll();
    }

    @Test
    public void muscleGroupSave() {
        MuscleGroup tongue = new MuscleGroup("Tongue", 99);
        this.muscleGroupRepository.save(tongue);
        assertEquals(1, muscleGroupRepository.count());
    }

    @Test
    public void muscleGroupSaveAllFindAll() {
        MuscleGroup tongue = new MuscleGroup("Tongue", 99);
        MuscleGroup calf = new MuscleGroup("Calf", 0);
        this.muscleGroupRepository.saveAll(Arrays.asList(tongue, calf));
        assertEquals(Arrays.asList(tongue, calf), muscleGroupRepository.findAll());
    }

    @Test
    public void muscleGroupExistsByIdFindById() {
        MuscleGroup tongue = this.muscleGroupRepository.save(new MuscleGroup("Tongue", 99));
        MuscleGroup calf = this.muscleGroupRepository.save(new MuscleGroup("Calf", 0));

        assertTrue(this.muscleGroupRepository.existsById(tongue.getId()));
        assertTrue(this.muscleGroupRepository.existsById(calf.getId()));
        assertFalse(this.muscleGroupRepository.existsById(tongue.getId() + calf.getId()));
        assertEquals(Optional.of(tongue), this.muscleGroupRepository.findById(tongue.getId()));
        assertEquals(Optional.of(calf), this.muscleGroupRepository.findById(calf.getId()));
        assertEquals(Optional.empty(), this.muscleGroupRepository.findById(999L));
    }
}
