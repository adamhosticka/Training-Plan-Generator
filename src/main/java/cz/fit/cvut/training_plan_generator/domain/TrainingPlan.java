package cz.fit.cvut.training_plan_generator.domain;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TrainingPlan implements DomainEntity<LocalDateTime> {
    private LocalDateTime createdAt = LocalDateTime.now();
    private List<Exercise> exercises;

    public TrainingPlan(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public LocalDateTime getId() {return createdAt;}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
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
                "createdAt=" + createdAt +
                ", exercises=" + exercises +
                '}';
    }
}
