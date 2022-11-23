package cz.fit.cvut.training_plan_generator.dao;

import cz.fit.cvut.training_plan_generator.domain.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TrainingPlanJpaRepository extends JpaRepository<TrainingPlan, LocalDateTime> {
}
