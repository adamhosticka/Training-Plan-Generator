package cz.cvut.fit.training_plan_generator.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class TrainingPlan {
    @Id
    private final LocalDateTime createdAt = LocalDateTime.now();
    @ManyToMany
    private List<Exercise> exercises;

    public TrainingPlan() {}

    public TrainingPlan(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public LocalDateTime getId() {return createdAt;}

    public LocalDateTime getCreatedAt() {
        return createdAt;
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
