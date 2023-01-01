package cz.cvut.fit.training_plan_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = DatabaseLoader.class)) // disable loading db
public class TrainingPlanGenerator {

	public static void main(String[] args) {
		SpringApplication.run(TrainingPlanGenerator.class, args);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.cors().disable()
				.csrf().disable();
		httpSecurity.authorizeRequests()
				.antMatchers(HttpMethod.DELETE, "/muscleGroups/*")
				.denyAll();
		return httpSecurity.build();
	}
}
