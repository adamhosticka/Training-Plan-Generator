package cz.cvut.fit.training_plan_generator.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Exercise {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    @NotNull
    private Category category;
    @ManyToMany
    @NotEmpty
    private Set<MuscleGroup> muscleGroups = new HashSet<>();

    public Exercise() {}

    public Exercise(String name, Category category, Set<MuscleGroup> muscleGroups) {
        this.name = name;
        this.category = category;
        this.muscleGroups = muscleGroups;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise exercise)) return false;
        return Objects.equals(id, exercise.id) && Objects.equals(name, exercise.name) && Objects.equals(category, exercise.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category);
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
