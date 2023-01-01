package cz.cvut.fit.training_plan_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = DatabaseLoader.class)) // disable loading db
public class TrainingPlanGenerator {

	public static void main(String[] args) {
		SpringApplication.run(TrainingPlanGenerator.class, args);
	}

}
