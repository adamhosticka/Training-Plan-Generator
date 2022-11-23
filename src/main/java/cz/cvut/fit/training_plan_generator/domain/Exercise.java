package cz.cvut.fit.training_plan_generator.domain;

import java.util.*;
public class Exercise implements DomainEntity<Long> {
    private final Long id;
    private String name;
    private Category category;
    private Set<MuscleGroup> muscleGroups = new HashSet<>();

    public Exercise(Long id, String name, Category category, Set<MuscleGroup> muscleGroups) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.muscleGroups = muscleGroups;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<MuscleGroup> getMuscleGroups() {
        return muscleGroups;
    }

    public void addMuscleGroup(MuscleGroup muscleGroups) {
        this.muscleGroups.add(Objects.requireNonNull(muscleGroups));
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", muscleGroups=" + muscleGroups +
                '}';
    }
}
