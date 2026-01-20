package cicd.order;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.devops.cicd.order.Order;
import com.devops.cicd.order.OrderValidator;

public class OrderValidatorTest {

    @Test
    void validOrder() {
        Order order = new Order("ORD-001", 5, 10.50, false);
        
        // Ne doit pas lever d'exception
        assertDoesNotThrow(() -> OrderValidator.validate(order));
    }

    @Test
    void nullOrderThrowsException() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> OrderValidator.validate(null)
        );
        assertEquals("order must not be null", exception.getMessage());
    }
}