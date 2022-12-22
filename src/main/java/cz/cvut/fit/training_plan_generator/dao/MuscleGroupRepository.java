package cz.cvut.fit.training_plan_generator.dao;

import cz.cvut.fit.training_plan_generator.domain.MuscleGroup;
import org.springframework.data.repository.CrudRepository;

public interface MuscleGroupRepository extends CrudRepository<MuscleGroup, Long> {
}
