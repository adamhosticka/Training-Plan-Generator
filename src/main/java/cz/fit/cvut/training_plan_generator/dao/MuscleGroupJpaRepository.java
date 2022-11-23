package cz.fit.cvut.training_plan_generator.dao;

import cz.fit.cvut.training_plan_generator.domain.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscleGroupJpaRepository extends JpaRepository<MuscleGroup, Long> {
}
