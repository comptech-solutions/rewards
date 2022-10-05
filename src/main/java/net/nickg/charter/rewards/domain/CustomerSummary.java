package net.nickg.charter.rewards.domain;

import org.springframework.data.util.Pair;

import java.time.Month;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

public class CustomerSummary {
    private Long customerId;
    private Map<Month, Integer> monthlyTotals;

    public CustomerSummary(Long customerId, Map<Month, Integer> monthlyTotals) {
        this.customerId = customerId;
        this.monthlyTotals = monthlyTotals;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Map<Month, Integer> getMonthlyTotals() {
        return monthlyTotals;
    }

    public int getOverallTotal() {
        return monthlyTotals
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
