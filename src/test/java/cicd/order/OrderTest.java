package cicd.order;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.devops.cicd.order.Order;

public class OrderTest {

    @Test
    void validOrder() {
        Order order = new Order("ORD-001", 5, 10.50, true);
        
        assertNotNull(order);
        assertEquals("ORD-001", order.getId());
        assertEquals(5, order.getQuantity());
        assertEquals(10.50, order.getUnitPrice());
        assertTrue(order.isPriority());
    }

    @Test
    void validNonPriorityOrder() {
        Order order = new Order("ORD-002", 10, 25.99, false);
        
        assertNotNull(order);
        assertEquals("ORD-002", order.getId());
        assertEquals(10, order.getQuantity());
        assertEquals(25.99, order.getUnitPrice());
        assertFalse(order.isPriority());
    }

    @Test
    void getTotalPrice() {
        Order order = new Order("ORD-003", 5, 10.50, false);
        assertEquals(52.50, order.getTotalPrice());
    }

    @Test
    void idIsTrimmed() {
        Order order = new Order("  ORD-004  ", 3, 15.0, false);
        assertEquals("ORD-004", order.getId());
    }

    @Test
    void nullIdThrowsException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Order(null, 5, 10.50, false)
        );
        assertEquals("id must not be null or empty", exception.getMessage());
    }

    @Test
    void emptyIdThrowsException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Order("", 5, 10.50, false)
        );
        assertEquals("id must not be null or empty", exception.getMessage());
    }

    @Test
    void whitespaceIdThrowsException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Order("   ", 5, 10.50, false)
        );
        assertEquals("id must not be null or empty", exception.getMessage());
    }

    @Test
    void zeroQuantityThrowsException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Order("ORD-005", 0, 10.50, false)
        );
        assertEquals("quantity must be greater than 0", exception.getMessage());
    }

    @Test
    void negativeQuantityThrowsException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Order("ORD-006", -5, 10.50, false)
        );
        assertEquals("quantity must be greater than 0", exception.getMessage());
    }

    @Test
    void zeroUnitPriceThrowsException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Order("ORD-007", 5, 0, false)
        );
        assertEquals("unitPrice must be greater than 0", exception.getMessage());
    }

    @Test
    void negativeUnitPriceThrowsException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Order("ORD-008", 5, -10.50, false)
        );
        assertEquals("unitPrice must be greater than 0", exception.getMessage());
    }

    @Test
    void equalsBasedOnId() {
        Order order1 = new Order("ORD-009", 5, 10.50, true);
        Order order2 = new Order("ORD-009", 10, 20.00, false);
        
        assertEquals(order1, order2);
        assertEquals(order1.hashCode(), order2.hashCode());
    }

    @Test
    void notEqualsWithDifferentId() {
        Order order1 = new Order("ORD-010", 5, 10.50, true);
        Order order2 = new Order("ORD-011", 5, 10.50, true);
        
        assertNotEquals(order1, order2);
    }

    @Test
    void equalsWithSameObject() {
        Order order = new Order("ORD-012", 5, 10.50, false);
        assertEquals(order, order);
    }

    @Test
    void notEqualsWithNull() {
        Order order = new Order("ORD-013", 5, 10.50, false);
        assertNotEquals(order, null);
    }

    @Test
    void notEqualsWithDifferentClass() {
        Order order = new Order("ORD-014", 5, 10.50, false);
        assertNotEquals(order, "ORD-014");
    }
}