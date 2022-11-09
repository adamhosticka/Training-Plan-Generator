package cz.fit.cvut.training_plan_generator.domain;

public class MuscleGroup implements DomainEntity<Long> {
    private final Long id;
    private String name;
    private int volume;

    public MuscleGroup(Long id, String name, int volume) {
        this.id = id;
        this.name = name;
        this.volume = volume;
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
