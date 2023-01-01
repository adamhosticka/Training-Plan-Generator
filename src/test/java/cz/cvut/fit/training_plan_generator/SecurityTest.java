package cz.cvut.fit.training_plan_generator;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrainingPlanGenerator.class)
@AutoConfigureMockMvc
class SecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void deleteMuscleGroupsForbidden() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/muscleGroups/1")
                )
                .andExpect(status().isForbidden())
                .andReturn();
    }

}
