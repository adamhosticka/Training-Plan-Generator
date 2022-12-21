package cz.cvut.fit.training_plan_generator;

import cz.cvut.fit.training_plan_generator.domain.Category;
import cz.cvut.fit.training_plan_generator.domain.Exercise;
import cz.cvut.fit.training_plan_generator.domain.MuscleGroup;
import cz.cvut.fit.training_plan_generator.domain.TrainingPlan;

import java.util.*;

public class TestBackend {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        MuscleGroup Chest = new MuscleGroup("Chest", 3);
        MuscleGroup Back = new MuscleGroup("Back", 3);
        MuscleGroup Legs = new MuscleGroup("Legs", 3);
        MuscleGroup Shoulders = new MuscleGroup("Shoulders", 2);
        MuscleGroup Triceps = new MuscleGroup("Triceps", 1);
        MuscleGroup Biceps = new MuscleGroup("Biceps", 1);

        Set<MuscleGroup> BenchPressMuscleGroups = new HashSet<>(Arrays.asList(Chest, Shoulders, Triceps));
        Category CompoundCategory = new Category("Compound Movements", 5, 4, 8);
        Exercise BenchPress = new Exercise("Bench Press", CompoundCategory, BenchPressMuscleGroups);
        Set<MuscleGroup> PullCategory = new HashSet<>(Arrays.asList(Back, Biceps));
        Exercise PullUp = new Exercise("Pull Up", CompoundCategory, PullCategory);
//        System.out.println(BenchPress);

        TrainingPlan trainingPlan1 = new TrainingPlan(Arrays.asList(BenchPress, PullUp));

        System.out.println(trainingPlan1);

    }
}
