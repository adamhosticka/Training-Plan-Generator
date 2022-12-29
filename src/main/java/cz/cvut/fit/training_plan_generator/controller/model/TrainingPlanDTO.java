package cz.cvut.fit.training_plan_generator.controller.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class TrainingPlanDTO {
    private String name;
    private Integer age;
    private String gender;
    private Integer timeToTrain;
    private String goal;
    private List<Long> muscleGroupIds;

    public TrainingPlanDTO() {}

    public TrainingPlanDTO(String name, Integer age, String gender, Integer timeToTrain, String goal, List<Long> muscleGroupIds) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.timeToTrain = timeToTrain;
        this.goal = goal;
        this.muscleGroupIds = muscleGroupIds;
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

    public List<Long> getMuscleGroupIds() {
        return muscleGroupIds;
    }

    public void setMuscleGroupIds(List<Long> muscleGroupIds) {
        this.muscleGroupIds = muscleGroupIds;
    }

    @Override
    public String toString() {
        return "TrainingPlanDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", timeToTrain=" + timeToTrain +
                ", goal='" + goal + '\'' +
                ", muscleGroupIds=" + muscleGroupIds +
                '}';
    }
}
