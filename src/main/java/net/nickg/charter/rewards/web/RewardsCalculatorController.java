package net.nickg.charter.rewards.web;

import net.nickg.charter.rewards.domain.CustomerSummary;
import net.nickg.charter.rewards.domain.RewardsSummary;
import net.nickg.charter.rewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RewardsService rewardsService;


    @GetMapping("calculate")
    public RewardsSummary calculateRewards() {
        return rewardsService.getRewardSummary();
    }
}
