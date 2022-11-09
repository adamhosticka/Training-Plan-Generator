package cz.fit.cvut.training_plan_generator.domain;

import java.util.Date;
import java.util.List;

public class TrainingPlan implements DomainEntity<Long> {
    private final Long id;
    private Date createdAt;
    private List<Exercise> exercises;

    public TrainingPlan(Long id, Date createdAt, List<Exercise> exercises) {
        this.id = id;
        this.createdAt = createdAt;
        this.exercises = exercises;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "TrainingPlan{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", exercises=" + exercises +
                '}';
    }
}
