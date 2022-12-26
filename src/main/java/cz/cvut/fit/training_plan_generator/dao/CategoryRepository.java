package cz.cvut.fit.training_plan_generator.dao;

import cz.cvut.fit.training_plan_generator.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:3000")
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
