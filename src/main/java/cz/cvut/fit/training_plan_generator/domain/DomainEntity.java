package cz.cvut.fit.training_plan_generator.domain;

import java.io.Serializable;

public interface DomainEntity<ID> extends Serializable {
    /**
     *
     * @return the primary key value of this instance
     */
    ID getId();
}
