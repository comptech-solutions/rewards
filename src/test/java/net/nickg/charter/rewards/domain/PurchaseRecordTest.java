package net.nickg.charter.rewards.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseRecordTest {

    @Test
    @DisplayName("Calculate points for zero total should return zero")
    void zeroTotal_ShouldBeZeroPoints() {
        PurchaseRecord purchase = new PurchaseRecord(BigDecimal.ZERO, LocalDate.now(), 1L);
        assertEquals(purchase.calculatePoints(), 0);
    }

    @Test
    @DisplayName("Calculate points for total of 50 should return zero")
    void totalOf50_ShouldBeZeroPoints() {
        PurchaseRecord purchase = new PurchaseRecord(new BigDecimal(50), LocalDate.now(), 1L);
        assertEquals(purchase.calculatePoints(), 0);
    }

    @Test
    @DisplayName("Calc points for total of 51 should be 1")
    void totalOf51_ShouldBeOnePoint() {
        PurchaseRecord purchase = new PurchaseRecord(new BigDecimal(51), LocalDate.now(), 1L);
        assertEquals(purchase.calculatePoints(), 1);
    }

    @Test
    @DisplayName("Calc points for total of 100 should be 50")
    void totalOf100_ShouldBe50Points() {
        PurchaseRecord purchase = new PurchaseRecord(new BigDecimal(100), LocalDate.now(), 1L);
        assertEquals(purchase.calculatePoints(), 50);
    }

    @Test
    @DisplayName("Calc points for total of 101 should be 52")
    void totalOf101_ShouldBe52Points() {
        PurchaseRecord purchase = new PurchaseRecord(new BigDecimal(101), LocalDate.now(), 1L);
        assertEquals(purchase.calculatePoints(), 52);
    }

}