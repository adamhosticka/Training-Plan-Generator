package cz.cvut.fit.training_plan_generator.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class MuscleGroup {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int volume;

    public MuscleGroup() {}

    public MuscleGroup(String name, int volume) {
        this.name = name;
        this.volume = volume;
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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "MuscleGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", volume=" + volume +
                '}';
    }
}
