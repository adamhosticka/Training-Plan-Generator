package cz.fit.cvut.training_plan_generator.dao;

import cz.fit.cvut.training_plan_generator.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
}
