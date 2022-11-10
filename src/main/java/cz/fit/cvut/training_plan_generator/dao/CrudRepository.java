package cz.fit.cvut.training_plan_generator.dao;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    Collection<T> findAll();

    boolean existsById(ID id);

    void deleteById(ID id);
}
