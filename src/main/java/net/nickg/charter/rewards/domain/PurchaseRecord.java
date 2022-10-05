package net.nickg.charter.rewards.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class PurchaseRecord {

    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal total;
    private LocalDate date;
    private Long customerId;

    public PurchaseRecord() {}

    public PurchaseRecord(BigDecimal total, LocalDate date, Long customerId) {
        this.total = total;
        this.date = date;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
