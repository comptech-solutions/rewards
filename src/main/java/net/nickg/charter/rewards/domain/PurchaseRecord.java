package net.nickg.charter.rewards.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class PurchaseRecord {

    public static final int ZERO = 0;
    public static final int ONE_POINT_MIN = 50;
    public static final int TWO_POINT_MIN = 100;
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

    public int calculatePoints() {
        int totalAsInt = total.intValue();
        if (totalAsInt > ONE_POINT_MIN) {
            if (totalAsInt > TWO_POINT_MIN) {
                return ( (totalAsInt - TWO_POINT_MIN) * 2 ) + ONE_POINT_MIN;
            }
            return (totalAsInt - ONE_POINT_MIN);
        }

        return ZERO;
    }

}
