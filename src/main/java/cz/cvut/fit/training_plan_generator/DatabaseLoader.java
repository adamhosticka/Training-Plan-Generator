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

import java.util.HashSet;
import java.util.List;

@Component
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
        List<MuscleGroup> muscleGroups = List.of(Chest, Back, Legs, Shoulders, Triceps, Biceps);
        this.muscleGroupRepository.saveAll(muscleGroups);

        Category compoundCategory = new Category("Compound Movement", 3, 5, 5);
        Category isolationExerciseStrength = new Category("Isolation Exercise - Strength", 2, 4, 8);
        Category isolationExerciseHypertrophy = new Category("Isolation Exercise - Hypertrophy", 1, 3, 12);
        List<Category> categories = List.of(compoundCategory, isolationExerciseStrength, isolationExerciseHypertrophy);
        this.categoryRepository.saveAll(categories);

        Exercise barbellBenchpress = new Exercise("Barbell Bench Press", compoundCategory, new HashSet<>(List.of(Chest, Shoulders, Triceps)));
        Exercise inclineBarbellBenchPress = new Exercise("Incline Barbell Bench Press", compoundCategory, new HashSet<>(List.of(Chest, Shoulders, Triceps)));
        Exercise barbellRow = new Exercise("Barbell Row", compoundCategory, new HashSet<>(List.of(Back, Biceps)));
        Exercise barbellShoulderPress = new Exercise("Barbell Shoulder Press", compoundCategory, new HashSet<>(List.of(Shoulders, Triceps)));
        Exercise deadlift = new Exercise("Deadlift", compoundCategory, new HashSet<>(List.of(Back, Legs)));
        Exercise bulgarianDeadlift = new Exercise("Bulgarian Deadlift", compoundCategory, new HashSet<>(List.of(Back, Legs)));
        Exercise barbellSquat = new Exercise("Barbell Squat", compoundCategory, new HashSet<>(List.of(Legs)));


        Exercise dumbbellBenchPress = new Exercise("Dumbbell Bench Press", isolationExerciseStrength, new HashSet<>(List.of(Chest, Shoulders, Triceps)));
        Exercise inclineDumbbellBenchPress = new Exercise("Incline Dumbbell Bench Press", isolationExerciseStrength, new HashSet<>(List.of(Chest, Shoulders, Triceps)));
        Exercise chestPress = new Exercise("Chest Press", isolationExerciseStrength, new HashSet<>(List.of(Chest, Shoulders)));
        Exercise dips = new Exercise("Dips", isolationExerciseStrength, new HashSet<>(List.of(Chest, Shoulders, Triceps)));
        Exercise latPulldown = new Exercise("Lat Pulldown", isolationExerciseStrength, new HashSet<>(List.of(Back)));
        Exercise dumbbellRow = new Exercise("Dumbbell Row", isolationExerciseStrength, new HashSet<>(List.of(Back)));
        Exercise legPress = new Exercise("Leg Press", isolationExerciseStrength, new HashSet<>(List.of(Legs)));
        Exercise bulgarianSplitSquat = new Exercise("Bulgarian Split Squat", isolationExerciseStrength, new HashSet<>(List.of(Legs)));
        Exercise hackSquat = new Exercise("Hack Squat", isolationExerciseStrength, new HashSet<>(List.of(Legs)));
        Exercise dumbbellShoulderPress = new Exercise("Dumbbell Shoulder Press", isolationExerciseStrength, new HashSet<>(List.of(Shoulders)));
        Exercise dumbbellOverheadTricepExtension = new Exercise("Dumbbell Overhead Tricep Extension", isolationExerciseStrength, new HashSet<>(List.of(Triceps)));
        Exercise barbellBicepCurl = new Exercise("Barbell Bicep Curl", isolationExerciseStrength, new HashSet<>(List.of(Biceps)));


        Exercise chestFly = new Exercise("Chest Fly", isolationExerciseHypertrophy, new HashSet<>(List.of(Chest)));
        Exercise cableChestFly = new Exercise("Cable Chest Fly", isolationExerciseHypertrophy, new HashSet<>(List.of(Chest)));
        Exercise cableRow = new Exercise("Cable Row", isolationExerciseHypertrophy, new HashSet<>(List.of(Back)));
        Exercise legCurl = new Exercise("Leg Curl", isolationExerciseHypertrophy, new HashSet<>(List.of(Legs)));
        Exercise legExtension = new Exercise("Leg Extension", isolationExerciseHypertrophy, new HashSet<>(List.of(Legs)));
        Exercise lateralRaises = new Exercise("Lateral Raises", isolationExerciseHypertrophy, new HashSet<>(List.of(Shoulders)));
        Exercise rearDeltFly = new Exercise("Rear Delt Fly", isolationExerciseHypertrophy, new HashSet<>(List.of(Shoulders)));
        Exercise dumbbellBicepCurl = new Exercise("Dumbbell Bicep Curl", isolationExerciseHypertrophy, new HashSet<>(List.of(Biceps)));
        Exercise dumbbellHammerCurl = new Exercise("Dumbbel Hammer Curl", isolationExerciseHypertrophy, new HashSet<>(List.of(Biceps)));
        Exercise tricepRopePushdown = new Exercise("Tricep Rope Pushdown", isolationExerciseHypertrophy, new HashSet<>(List.of(Triceps)));
        Exercise cableOverheadTricepExtension = new Exercise("Cable Overhead Tricep Extension", isolationExerciseHypertrophy, new HashSet<>(List.of(Triceps)));

        List<Exercise> exercises = List.of(
                barbellBenchpress, inclineBarbellBenchPress, barbellRow, barbellShoulderPress, deadlift, bulgarianDeadlift, barbellSquat,
                dumbbellBenchPress, inclineDumbbellBenchPress, chestPress, dips, latPulldown, dumbbellRow, cableRow, legPress, bulgarianSplitSquat, hackSquat, dumbbellShoulderPress, dumbbellOverheadTricepExtension, barbellBicepCurl,
                chestFly, cableChestFly, legCurl, legExtension, lateralRaises, rearDeltFly, dumbbellBicepCurl, dumbbellHammerCurl, tricepRopePushdown, cableOverheadTricepExtension
        );
        this.exerciseRepository.saveAll(exercises);

        TrainingPlan chestFocusTrainingPlan = new TrainingPlan(
                "Chest focus", 18, "Male", 20, "Strength",
                new HashSet<>(List.of(Chest)),
                List.of(barbellBenchpress, inclineDumbbellBenchPress, chestPress, chestFly)
        );
        List<TrainingPlan> trainingPlans = List.of(
                chestFocusTrainingPlan
        );
        this.trainingPlanRepository.saveAll(trainingPlans);
    }
}