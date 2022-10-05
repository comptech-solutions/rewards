package net.nickg.charter.rewards.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;




@SpringBootTest
@AutoConfigureMockMvc
class RewardsCalculatorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void calculateRewardsReturnsRewardsSummary() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/rewards/calculate")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerSummaries.length()").value(2));

    }
}