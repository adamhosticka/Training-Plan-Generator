package cz.fit.cvut.training_plan_generator.dao;

import cz.fit.cvut.training_plan_generator.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseJpaRepository extends JpaRepository<Exercise, Long> {
}
