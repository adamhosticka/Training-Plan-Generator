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

import java.util.HashSet;
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
//        List<MuscleGroup> allMuscleGroups = (List<MuscleGroup>) muscleGroupRepository.findAll();
        List<Exercise> allExercises = (List<Exercise>) exerciseRepository.findAll();
//        List<Category> allCategories = (List<Category>) categoryRepository.findAll();
        List<Exercise> planExercises = allExercises.stream()
                .filter(exercise -> {
                    Set<MuscleGroup> mgs = new HashSet<>(plan.getMuscleGroups());
                    mgs.retainAll(exercise.getMuscleGroups());
                    return !mgs.isEmpty();
                })
                .toList();
        return planExercises;
    }
}
