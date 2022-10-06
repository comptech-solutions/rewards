package net.nickg.charter.rewards.service;


import net.nickg.charter.rewards.domain.CustomerSummary;
import net.nickg.charter.rewards.domain.RewardsSummary;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RewardsService {

    private PurchaseRecordService purchaseRecordService;

    public RewardsService(PurchaseRecordService purchaseRecordService) {
        this.purchaseRecordService = purchaseRecordService;
    }

    public RewardsSummary getRewardSummary() {
        List<Long> customerIds = purchaseRecordService.getAllCustomerIds();
        System.out.println("Customer Ids:\n");
        customerIds.stream().forEach(System.out::println);

        List<CustomerSummary> customerSummaries = new ArrayList<>();

        customerIds.stream()
                .forEach(id -> {
                    System.out.println("Processing id: " + id);
                    CustomerSummary summary = new CustomerSummary(id, new HashMap<>());
                    System.out.println("Created new CustomerSummary");
                    purchaseRecordService.getAllForCustomer(id).stream()
                            .filter(r -> r.calculatePoints() > 0)
                            .forEach(purchaseRecord -> {
                                System.out.println("\tprocessing PurchaseRecord: " + purchaseRecord);
                                Month month = purchaseRecord.getDate().getMonth();
                                Integer points = purchaseRecord.calculatePoints();
                                System.out.println("\t\tmonth=" + month + ", points=" + points);
                                summary.addToTotals(month, points);
                                System.out.println("\t\tsummary updated to: " + summary);
                            });
                    customerSummaries.add(summary);
                });

        customerSummaries.stream().forEach(System.out::println);

        return new RewardsSummary(customerSummaries);
    }

}
