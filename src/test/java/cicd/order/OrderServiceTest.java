package cicd.order;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devops.cicd.order.Order;
import com.devops.cicd.order.OrderService;

public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    void nullOrderThrowsException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> orderService.computeTotal(null)
        );
        assertEquals("order must not be null", exception.getMessage());
    }

    @Test
    void simpleOrderWithoutDiscount() {
        // Sous-total: 5 * 10 = 50 (< 100, pas de remise)
        // Pas prioritaire, pas de frais
        // Total: 50.00
        Order order = new Order("ORD-001", 5, 10.0, false);
        assertEquals(50.00, orderService.computeTotal(order));
    }

    @Test
    void orderWithDiscount() {
        // Sous-total: 10 * 15 = 150 (>= 100, remise de 5%)
        // Total: 150 * 0.95 = 142.50
        Order order = new Order("ORD-002", 10, 15.0, false);
        assertEquals(142.50, orderService.computeTotal(order));
    }

    @Test
    void priorityOrderWithoutDiscount() {
        // Sous-total: 5 * 10 = 50 (< 100, pas de remise)
        // Prioritaire: +9.99
        // Total: 50 + 9.99 = 59.99
        Order order = new Order("ORD-003", 5, 10.0, true);
        assertEquals(59.99, orderService.computeTotal(order));
    }

    @Test
    void priorityOrderWithDiscount() {
        // Sous-total: 10 * 15 = 150 (>= 100, remise de 5%)
        // Après remise: 150 * 0.95 = 142.50
        // Prioritaire: +9.99
        // Total: 142.50 + 9.99 = 152.49
        Order order = new Order("ORD-004", 10, 15.0, true);
        assertEquals(152.49, orderService.computeTotal(order));
    }

    @Test
    void orderAtDiscountThreshold() {
        // Sous-total: 10 * 10 = 100 (== 100, remise de 5%)
        // Total: 100 * 0.95 = 95.00
        Order order = new Order("ORD-005", 10, 10.0, false);
        assertEquals(95.00, orderService.computeTotal(order));
    }

    @Test
    void orderJustBelowThreshold() {
        // Sous-total: 10 * 9.90 = 99 (< 100, pas de remise)
        // Total: 99.00
        Order order = new Order("ORD-006", 10, 9.90, false);
        assertEquals(99.00, orderService.computeTotal(order));
    }

    @Test
    void orderJustAboveThreshold() {
        // Sous-total: 10 * 10.10 = 101 (>= 100, remise de 5%)
        // Total: 101 * 0.95 = 95.95
        Order order = new Order("ORD-007", 10, 10.10, false);
        assertEquals(95.95, orderService.computeTotal(order));
    }

    @Test
    void roundingToTwoDecimals() {
        // Sous-total: 3 * 10.333 = 30.999
        // Total arrondi: 31.00
        Order order = new Order("ORD-008", 3, 10.333, false);
        assertEquals(31.00, orderService.computeTotal(order));
    }

    @Test
    void complexCalculationWithRounding() {
        // Sous-total: 7 * 15.50 = 108.50 (>= 100, remise de 5%)
        // Après remise: 108.50 * 0.95 = 103.075
        // Prioritaire: +9.99 = 113.065
        // Total arrondi: 113.06 (pas 113.07)
        Order order = new Order("ORD-009", 7, 15.50, true);
        assertEquals(113.06, orderService.computeTotal(order));
    }

    @Test
    void minimumOrder() {
        // Sous-total: 1 * 0.01 = 0.01
        // Total: 0.01
        Order order = new Order("ORD-010", 1, 0.01, false);
        assertEquals(0.01, orderService.computeTotal(order));
    }

    @Test
    void largeOrder() {
        // Sous-total: 100 * 50 = 5000 (>= 100, remise de 5%)
        // Après remise: 5000 * 0.95 = 4750.00
        // Total: 4750.00
        Order order = new Order("ORD-011", 100, 50.0, false);
        assertEquals(4750.00, orderService.computeTotal(order));
    }

    @Test
    void priorityFeeAtThreshold() {
        // Sous-total: 10 * 10 = 100 (== 100, remise de 5%)
        // Après remise: 100 * 0.95 = 95.00
        // Prioritaire: +9.99
        // Total: 95.00 + 9.99 = 104.99
        Order order = new Order("ORD-012", 10, 10.0, true);
        assertEquals(104.99, orderService.computeTotal(order));
    }

    @Test
    void smallPriorityOrder() {
        // Sous-total: 1 * 5.50 = 5.50 (< 100, pas de remise)
        // Prioritaire: +9.99
        // Total: 5.50 + 9.99 = 15.49
        Order order = new Order("ORD-013", 1, 5.50, true);
        assertEquals(15.49, orderService.computeTotal(order));
    }

    @Test
    void decimalQuantityPrices() {
        // Sous-total: 3 * 33.33 = 99.99 (< 100, pas de remise)
        // Total: 99.99
        Order order = new Order("ORD-014", 3, 33.33, false);
        assertEquals(99.99, orderService.computeTotal(order));
    }

    @Test
    void roundingUpCase() {
        // Sous-total: 10 * 10.006 = 100.06 (>= 100, remise de 5%)
        // Après remise: 100.06 * 0.95 = 95.057
        // Total arrondi: 95.06
        Order order = new Order("ORD-015", 10, 10.006, false);
        assertEquals(95.06, orderService.computeTotal(order));
    }

    @Test
    void roundingDownCase() {
        // Sous-total: 10 * 10.004 = 100.04 (>= 100, remise de 5%)
        // Après remise: 100.04 * 0.95 = 95.038
        // Total arrondi: 95.04
        Order order = new Order("ORD-016", 10, 10.004, false);
        assertEquals(95.04, orderService.computeTotal(order));
    }
}