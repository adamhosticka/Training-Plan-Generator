package cz.cvut.fit.training_plan_generator.service;

import cz.cvut.fit.training_plan_generator.dao.ExerciseRepository;
import cz.cvut.fit.training_plan_generator.domain.Exercise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercisesDetailService {

    private final ExerciseRepository exerciseRepository;

    public ExercisesDetailService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getExercisesDetail() {
        return (List<Exercise>) this.exerciseRepository.findAll();
    }
}
