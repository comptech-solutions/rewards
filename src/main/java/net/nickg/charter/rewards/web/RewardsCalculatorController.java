package net.nickg.charter.rewards.web;

import net.nickg.charter.rewards.domain.CustomerSummary;
import net.nickg.charter.rewards.domain.RewardsSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards/")
public class RewardsCalculatorController {

    @GetMapping("calculate")
    public RewardsSummary calculateRewards() {
        List<CustomerSummary> customerSummaries = new ArrayList<>();

        var sums = Map.of(Month.APRIL, 500, Month.MAY, 600, Month.JUNE, 700);
        customerSummaries.add(new CustomerSummary(100L, sums));

        sums = Map.of(Month.APRIL, 50, Month.MAY, 0, Month.JUNE, 10);
        customerSummaries.add(new CustomerSummary(200L, sums));

        RewardsSummary summary = new RewardsSummary(customerSummaries);

        return summary;
    }
}
