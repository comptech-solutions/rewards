package net.nickg.charter.rewards;

import net.nickg.charter.rewards.domain.RewardsSummary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

/*    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void endpointTest() {
        // arrange

        // act
        restTemplate.getForEntity("/api/rewards/calculator", RewardsSummary.class);
        // assert
    }*/
}
