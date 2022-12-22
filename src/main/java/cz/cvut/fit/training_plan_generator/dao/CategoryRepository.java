package cz.cvut.fit.training_plan_generator.dao;

import cz.cvut.fit.training_plan_generator.domain.Category;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Long> {
}
