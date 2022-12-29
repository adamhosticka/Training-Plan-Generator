package cz.cvut.fit.training_plan_generator.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Entity
public class TrainingPlan {
    @Id @GeneratedValue
    private Long id;
    private final LocalDateTime createdAt = LocalDateTime.now();
    @NotBlank
    private String name;
    private Integer age;
    private String gender;
    private Integer timeToTrain;
    private String goal;
    @ManyToMany
    @NotEmpty
    private Set<MuscleGroup> muscleGroups;

    @ManyToMany
    private List<Exercise> exercises;

    public TrainingPlan() {}

    public TrainingPlan(String name, Integer age, String gender, Integer timeToTrain, String goal, Set<MuscleGroup> muscleGroups) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.timeToTrain = timeToTrain;
        this.goal = goal;
        this.muscleGroups = muscleGroups;
    }

    public TrainingPlan(String name, Integer age, String gender, Integer timeToTrain, String goal, Set<MuscleGroup> muscleGroups, List<Exercise> exercises) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.timeToTrain = timeToTrain;
        this.goal = goal;
        this.muscleGroups = muscleGroups;
        this.exercises = exercises;
    }

    public Long getId() {return id;}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getTimeToTrain() {
        return timeToTrain;
    }

    public void setTimeToTrain(Integer timeToTrain) {
        this.timeToTrain = timeToTrain;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Set<MuscleGroup> getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(Set<MuscleGroup> muscleGroups) {
        this.muscleGroups = muscleGroups;
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
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", timeToTrain=" + timeToTrain +
                ", goal='" + goal + '\'' +
                ", muscleGroups=" + muscleGroups +
                ", exercises=" + exercises +
                '}';
    }
}
