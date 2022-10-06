package net.nickg.charter.rewards.domain;

import net.nickg.charter.rewards.service.PurchaseRecordService;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class CustomerSummary {
    private Long customerId;
    private Map<Month, Integer> monthlyTotals = new HashMap<>();

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

    public void addToTotals(Month month, Integer points) {
        System.out.println("addToTotals() with month=" + month + ", points=" + points);
        monthlyTotals.merge(month, points, (k, v) -> v + points);
        System.out.println("monthlyTotals for " + month + " is now: " + monthlyTotals.get(month));
    }

    public void processPurchaseRecord(PurchaseRecord purchaseRecord) {
        var month = purchaseRecord.getDate().getMonth();
        var points = purchaseRecord.calculatePoints();
    }

    @Override
    public String toString() {
        return "CustomerSummary{" +
                "customerId=" + customerId +
                ", monthlyTotals=" + monthlyTotals +
                '}';
    }
}
