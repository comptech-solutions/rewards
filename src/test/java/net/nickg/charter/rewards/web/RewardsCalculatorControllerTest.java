package net.nickg.charter.rewards.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class RewardsCalculatorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Call to /api/rewards/calculate should return a RewardsSummary")
    void calculateRewardsReturnsRewardsSummary() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/rewards/calculate")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.customerSummaries[:1].monthlyTotals.length()").value(3));

    }
}