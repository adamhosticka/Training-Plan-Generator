package cz.cvut.fit.training_plan_generator;

import cz.cvut.fit.training_plan_generator.dao.CategoryRepository;
import cz.cvut.fit.training_plan_generator.dao.ExerciseRepository;
import cz.cvut.fit.training_plan_generator.dao.MuscleGroupRepository;
import cz.cvut.fit.training_plan_generator.dao.TrainingPlanRepository;
import cz.cvut.fit.training_plan_generator.domain.Category;
import cz.cvut.fit.training_plan_generator.domain.Exercise;
import cz.cvut.fit.training_plan_generator.domain.MuscleGroup;
import cz.cvut.fit.training_plan_generator.domain.TrainingPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// Uncomment annotation to load db
//@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ExerciseRepository exerciseRepository;
    private final CategoryRepository categoryRepository;
    private final MuscleGroupRepository muscleGroupRepository;
    private final TrainingPlanRepository trainingPlanRepository;

    @Autowired
    public DatabaseLoader(
            ExerciseRepository exerciseRepository,
            CategoryRepository categoryRepository,
            MuscleGroupRepository muscleGroupRepository,
            TrainingPlanRepository trainingPlanRepository
    ) {
        this.exerciseRepository = exerciseRepository;
        this.categoryRepository = categoryRepository;
        this.muscleGroupRepository = muscleGroupRepository;
        this.trainingPlanRepository = trainingPlanRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        MuscleGroup Chest = new MuscleGroup("Chest", 3);
        MuscleGroup Back = new MuscleGroup("Back", 3);
        MuscleGroup Legs = new MuscleGroup("Legs", 3);
        MuscleGroup Shoulders = new MuscleGroup("Shoulders", 2);
        MuscleGroup Triceps = new MuscleGroup("Triceps", 1);
        MuscleGroup Biceps = new MuscleGroup("Biceps", 1);
        List<MuscleGroup> muscleGroups = Arrays.asList(Chest, Back, Legs, Shoulders, Triceps, Biceps);
        this.muscleGroupRepository.saveAll(muscleGroups);

        Category compoundCategory = new Category("Compound Movements", 3, 5, 5);
        Category isolationExercisesStrength = new Category("Isolation Exercises - Strength", 2, 4, 8);
        Category isolationExercisesHypertrophy = new Category("Isolation Exercises - Hypertrophy", 1, 3, 12);
        List<Category> categories = Arrays.asList(compoundCategory, isolationExercisesStrength, isolationExercisesHypertrophy);
        this.categoryRepository.saveAll(categories);

        Exercise benchPress = new Exercise("Bench Press", compoundCategory, new HashSet<>(Arrays.asList(Chest, Shoulders, Triceps)));
        Exercise deadlift = new Exercise("Deadlift", compoundCategory, new HashSet<>(Arrays.asList(Back, Legs)));
        Exercise latPulldown = new Exercise("Lat Pulldown", isolationExercisesStrength, new HashSet<>(Arrays.asList(Back, Biceps)));
        Exercise legPress = new Exercise("Leg Press", isolationExercisesStrength, new HashSet<>(Arrays.asList(Legs)));
        Exercise chestPress = new Exercise("Chest Press", isolationExercisesStrength, new HashSet<>(Arrays.asList(Chest, Shoulders)));
        Exercise lateralRaises = new Exercise("Lateral Raises", isolationExercisesHypertrophy, new HashSet<>(Arrays.asList(Shoulders)));
        Exercise bicepCurl = new Exercise("Bicep Curl", isolationExercisesHypertrophy, new HashSet<>(Arrays.asList(Biceps)));
        Exercise tricepPulldown = new Exercise("Tricep Pulldown", isolationExercisesHypertrophy, new HashSet<>(Arrays.asList(Triceps)));
        List<Exercise> exercises = Arrays.asList(
                benchPress, deadlift, latPulldown, legPress, chestPress, lateralRaises, bicepCurl, tricepPulldown
        );
        this.exerciseRepository.saveAll(exercises);

        TrainingPlan chestFocusTrainingPlan = new TrainingPlan(
                "Chest focus", 18, "Male", 20, "Strength",
                Arrays.asList(Chest),
                Arrays.asList(benchPress, chestPress)
        );
        List<TrainingPlan> trainingPlans = Arrays.asList(
                chestFocusTrainingPlan
        );
        this.trainingPlanRepository.saveAll(trainingPlans);
    }
}