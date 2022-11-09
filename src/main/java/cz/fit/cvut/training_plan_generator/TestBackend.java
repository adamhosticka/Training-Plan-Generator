package cz.fit.cvut.training_plan_generator;

import cz.fit.cvut.training_plan_generator.domain.Category;
import cz.fit.cvut.training_plan_generator.domain.Exercise;
import cz.fit.cvut.training_plan_generator.domain.MuscleGroup;
import cz.fit.cvut.training_plan_generator.domain.TrainingPlan;

import java.util.*;

public class TestBackend {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        MuscleGroup Chest = new MuscleGroup(1L, "Chest", 3);
        MuscleGroup Back = new MuscleGroup(2L, "Back", 3);
        MuscleGroup Legs = new MuscleGroup(3L, "Legs", 3);
        MuscleGroup Shoulders = new MuscleGroup(4L, "Shoulders", 2);
        MuscleGroup Triceps = new MuscleGroup(5L, "Triceps", 1);
        MuscleGroup Biceps = new MuscleGroup(6L, "Biceps", 1);

        Set<MuscleGroup> BenchPressMuscleGroups = new HashSet<>(Arrays.asList(Chest, Shoulders, Triceps));
        Category CompoundCategory = new Category(1L, "Compound Movements", 5, 4, 8);
        Exercise BenchPress = new Exercise(1L, "Bench Press", CompoundCategory, BenchPressMuscleGroups);
        Set<MuscleGroup> PullCategory = new HashSet<>(Arrays.asList(Back, Biceps));
        Exercise PullUp = new Exercise(1L, "Pull Up", CompoundCategory, PullCategory);
//        System.out.println(BenchPress);

        TrainingPlan trainingPlan1 = new TrainingPlan(1L, new Date(System.currentTimeMillis()), Arrays.asList(BenchPress, PullUp));

        System.out.println(trainingPlan1);

    }
}
