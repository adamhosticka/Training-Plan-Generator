package cz.cvut.fit.training_plan_generator.service;

import cz.cvut.fit.training_plan_generator.controller.model.TrainingPlanDTO;
import cz.cvut.fit.training_plan_generator.dao.ExerciseRepository;
import cz.cvut.fit.training_plan_generator.dao.MuscleGroupRepository;
import cz.cvut.fit.training_plan_generator.dao.TrainingPlanRepository;
import cz.cvut.fit.training_plan_generator.domain.Exercise;
import cz.cvut.fit.training_plan_generator.domain.MuscleGroup;
import cz.cvut.fit.training_plan_generator.domain.TrainingPlan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TrainingPlanGeneratorService {
    private final MuscleGroupRepository muscleGroupRepository;
    private final ExerciseRepository exerciseRepository;
    private final TrainingPlanRepository trainingPlanRepository;


    public TrainingPlanGeneratorService(MuscleGroupRepository muscleGroupRepository, ExerciseRepository exerciseRepository, TrainingPlanRepository trainingPlanRepository) {
        this.muscleGroupRepository = muscleGroupRepository;
        this.exerciseRepository = exerciseRepository;
        this.trainingPlanRepository = trainingPlanRepository;
    }

    public TrainingPlan createPlan(@RequestBody TrainingPlanDTO planDto) {
        TrainingPlan plan = convertDto(planDto);
        plan.setExercises(generateExercises(plan));
        trainingPlanRepository.save(plan);
        return plan;
    }

    public TrainingPlan convertDto(TrainingPlanDTO trainingPlanDTO) {
        TrainingPlan trainingPlan = new TrainingPlan();
        Set<MuscleGroup> muscleGroupSet = StreamSupport
                .stream(muscleGroupRepository.findAllById(trainingPlanDTO.getMuscleGroupIds()).spliterator(), false)
                .collect(Collectors.toSet());
        trainingPlan.setName(trainingPlanDTO.getName());
        trainingPlan.setAge(trainingPlanDTO.getAge());
        trainingPlan.setGender(trainingPlanDTO.getGender());
        trainingPlan.setTimeToTrain(trainingPlanDTO.getTimeToTrain());
        trainingPlan.setGoal(trainingPlanDTO.getGoal());
        trainingPlan.setMuscleGroups(muscleGroupSet);

        return trainingPlan;
    }

    public List<Exercise> generateExercises(TrainingPlan plan) {
        List<Exercise> allExercises = (List<Exercise>) exerciseRepository.findAll();
        int nrOfExercises = setNumberOfExercises(plan.getTimeToTrain() / 10);
        List<Exercise> planExercises = allExercises.stream()
                .filter(exercise -> plan.getMuscleGroups().containsAll(exercise.getMuscleGroups()))
                .toList();
        int startIndex, endIndex;
        if(plan.getGender().equalsIgnoreCase("male")) nrOfExercises = setNumberOfExercises(nrOfExercises + 1);
        if(plan.getGoal().equalsIgnoreCase("hypertrophy")) {
            nrOfExercises = setNumberOfExercises(nrOfExercises + 1);
            endIndex = planExercises.size();
            startIndex = Math.max(0, endIndex - nrOfExercises);
        } else {
            startIndex = 0;
            endIndex = nrOfExercises;
        }
        return planExercises.subList(startIndex, endIndex);
    }

    public int setNumberOfExercises(int newNumber) {
        return Math.min(newNumber, 12);
    }
}
