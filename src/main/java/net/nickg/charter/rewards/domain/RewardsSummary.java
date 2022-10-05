package net.nickg.charter.rewards.domain;

import java.util.List;

public class RewardsSummary {

    private List<CustomerSummary> customerSummaries;

    public RewardsSummary(List<CustomerSummary> customerSummaries) {
        this.customerSummaries = customerSummaries;
    }

    public List<CustomerSummary> getCustomerSummaries() {
        return customerSummaries;
    }
}
