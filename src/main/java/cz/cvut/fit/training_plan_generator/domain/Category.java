package cz.cvut.fit.training_plan_generator.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Category {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int complexity;
    private int optimal_set_count;
    private int optimal_rep_count;

    public Category() {}

    public Category(String name, int complexity, int optimal_set_count, int optimal_rep_count) {
        this.name = name;
        this.complexity = complexity;
        this.optimal_set_count = optimal_set_count;
        this.optimal_rep_count = optimal_rep_count;
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

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getOptimal_set_count() {
        return optimal_set_count;
    }

    public void setOptimal_set_count(int optimal_set_count) {
        this.optimal_set_count = optimal_set_count;
    }

    public int getOptimal_rep_count() {
        return optimal_rep_count;
    }

    public void setOptimal_rep_count(int optimal_rep_count) {
        this.optimal_rep_count = optimal_rep_count;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", complexity=" + complexity +
                ", optimal_set_count=" + optimal_set_count +
                ", optimal_rep_count=" + optimal_rep_count +
                '}';
    }
}
