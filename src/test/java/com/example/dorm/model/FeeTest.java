package com.example.dorm.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Fee}.
 */
class FeeTest {

    @Test
    void testGettersAndSetters() {
        Fee f = new Fee();
        f.setId(1L);
        Contract c = new Contract();
        f.setContract(c);
        f.setType(FeeType.CLEANING);
        f.setAmount(BigDecimal.TEN);
        LocalDate due = LocalDate.now();
        f.setDueDate(due);
        f.setPaymentStatus(PaymentStatus.PAID);

        assertEquals(1L, f.getId());
        assertSame(c, f.getContract());
        assertEquals(FeeType.CLEANING, f.getType());
        assertEquals(BigDecimal.TEN, f.getAmount());
        assertEquals(due, f.getDueDate());
        assertEquals(PaymentStatus.PAID, f.getPaymentStatus());
        assertNotNull(f.toString());
    }
}
