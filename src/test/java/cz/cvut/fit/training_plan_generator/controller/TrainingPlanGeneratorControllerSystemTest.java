package cz.cvut.fit.training_plan_generator.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.cvut.fit.training_plan_generator.TrainingPlanGenerator;
import cz.cvut.fit.training_plan_generator.controller.model.TrainingPlanDTO;
import cz.cvut.fit.training_plan_generator.dao.CategoryRepository;
import cz.cvut.fit.training_plan_generator.dao.ExerciseRepository;
import cz.cvut.fit.training_plan_generator.dao.MuscleGroupRepository;
import cz.cvut.fit.training_plan_generator.dao.TrainingPlanRepository;
import cz.cvut.fit.training_plan_generator.domain.Category;
import cz.cvut.fit.training_plan_generator.domain.Exercise;
import cz.cvut.fit.training_plan_generator.domain.MuscleGroup;
import cz.cvut.fit.training_plan_generator.domain.TrainingPlan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrainingPlanGenerator.class)
@AutoConfigureMockMvc
public class TrainingPlanGeneratorControllerSystemTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MuscleGroupRepository muscleGroupRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @AfterEach
    public void clearRepositories() {
        this.trainingPlanRepository.deleteAll();
        this.exerciseRepository.deleteAll();
        this.muscleGroupRepository.deleteAll();
        this.categoryRepository.deleteAll();
    }

    @Test
    public void createPlanIfPlanValid() throws Exception {
        MuscleGroup chest = this.muscleGroupRepository.save(new MuscleGroup("Chest", 3));
        Category compound = this.categoryRepository.save(new Category("Compound", 3, 5, 5));
        Exercise benchPress = this.exerciseRepository.save(new Exercise("Bench Press", compound, new HashSet<>(List.of(chest))));
        TrainingPlanDTO planDTOBlankName = new TrainingPlanDTO("", 18, "Male", 44, "Strength", List.of(chest.getId()));
        TrainingPlanDTO planDTONoMGs = new TrainingPlanDTO("NoMGs", 18, "Male", 44, "Strength", List.of());
        TrainingPlanDTO planDTOCorrect = new TrainingPlanDTO("Correct plan", 18, "Male", 44, "Strength", List.of(chest.getId()));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String blankNameBody = mapper.writeValueAsString(planDTOBlankName);
        String noMGsBody = mapper.writeValueAsString(planDTONoMGs);
        getMvcResult(blankNameBody, 400);
        getMvcResult(noMGsBody, 400);

        String correctPlanBody = mapper.writeValueAsString(planDTOCorrect);
        MvcResult correctPlanResult = getMvcResult(correctPlanBody, 201);

        String correctPlanResponseBody = correctPlanResult.getResponse().getContentAsString();
        TrainingPlan correctPlan = mapper.readValue(correctPlanResponseBody, TrainingPlan.class);

        assertNotNull(correctPlan.getCreatedAt());
        assertEquals(planDTOCorrect.getName(), correctPlan.getName());
        assertEquals(planDTOCorrect.getAge(), correctPlan.getAge());
        assertEquals(planDTOCorrect.getTimeToTrain(), correctPlan.getTimeToTrain());
        assertEquals(planDTOCorrect.getGender(), correctPlan.getGender());
        assertEquals(planDTOCorrect.getGoal(), correctPlan.getGoal());
        assertEquals(List.of(benchPress), correctPlan.getExercises());
    }

    public MvcResult getMvcResult(String postBody, int expectedStatus) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                        .post("/createPlan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postBody)
                )
                .andExpect(status().is(expectedStatus))
                .andReturn();
    }
}
