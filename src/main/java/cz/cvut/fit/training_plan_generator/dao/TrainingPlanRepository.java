package cz.cvut.fit.training_plan_generator.dao;

import cz.cvut.fit.training_plan_generator.domain.TrainingPlan;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDateTime;

public interface TrainingPlanRepository extends CrudRepository<TrainingPlan, LocalDateTime> {
}
