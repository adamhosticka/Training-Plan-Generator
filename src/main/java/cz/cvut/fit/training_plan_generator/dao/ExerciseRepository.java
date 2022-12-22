package cz.cvut.fit.training_plan_generator.dao;

import cz.cvut.fit.training_plan_generator.domain.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
}
