package cz.cvut.fit.training_plan_generator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.cvut.fit.training_plan_generator.TrainingPlanGenerator;
import cz.cvut.fit.training_plan_generator.controller.model.TrainingPlanDTO;
import cz.cvut.fit.training_plan_generator.dao.MuscleGroupRepository;
import cz.cvut.fit.training_plan_generator.domain.MuscleGroup;
import cz.cvut.fit.training_plan_generator.domain.TrainingPlan;
import cz.cvut.fit.training_plan_generator.service.TrainingPlanGeneratorService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrainingPlanGenerator.class)
@AutoConfigureMockMvc
public class TrainingPlanGeneratorControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrainingPlanGeneratorService trainingPlanGeneratorService;
    @Autowired
    private TrainingPlanGeneratorController trainingPlanGeneratorController;
    @Autowired
    private MuscleGroupRepository muscleGroupRepository;

    @Before
    public void exceptionMapperSetup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(this.trainingPlanGeneratorController)
                .setControllerAdvice(new ExceptionMapper())
                .build();
    }


    @Test
    public void returnCreatedIfServiceReturnsPlan() throws Exception {
        MuscleGroup tongue = this.muscleGroupRepository.save(new MuscleGroup("Tongue", 99));
        TrainingPlanDTO planDTO = new TrainingPlanDTO("PlanName", 18, "Male", 44, "Strength", List.of(tongue.getId()));
        TrainingPlan plan = new TrainingPlan("PlanName", 18, "Male", 44, "Strength", new HashSet<>(List.of(tongue)));

        Mockito.when(this.trainingPlanGeneratorService.createPlan(planDTO)).thenReturn(plan);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String postBody = mapper.writeValueAsString(planDTO);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/createPlan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postBody)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.is(planDTO.getName())))
                .andExpect(jsonPath("$.age", Matchers.is(planDTO.getAge())))
                .andExpect(jsonPath("$.goal", Matchers.is(planDTO.getGoal())))
                .andExpect(jsonPath("$.timeToTrain", Matchers.is(planDTO.getTimeToTrain())))
                .andExpect(jsonPath("$.gender", Matchers.is(planDTO.getGender())))
                .andExpect(jsonPath("$.muscleGroups").isNotEmpty())
                .andReturn();
    }

    @Test
    public void returnBadRequestIfServiceThrowsException() throws Exception {
        TrainingPlanDTO planDTO = new TrainingPlanDTO("", 18, "Male", 44, "Strength", List.of());

        Mockito.when(this.trainingPlanGeneratorService.createPlan(planDTO)).thenThrow(ConstraintViolationException.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String postBody = mapper.writeValueAsString(planDTO);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/createPlan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postBody)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
