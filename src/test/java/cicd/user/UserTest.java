package cicd.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.devops.cicd.user.Role;
import com.devops.cicd.user.User;

public class UserTest {

    @Test
    void isValidAdminUser() {
        User user = new User("user@test.fr", "Password_1234", Role.ADMIN);

        assertNotNull(user);
        assertEquals("user@test.fr", user.getEmail());
        assertEquals("Password_1234", user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void isValidSimpleUser() {
        User user = new User("user@test.fr", "Password_1234", Role.USER);

        assertNotNull(user);
        assertEquals("user@test.fr", user.getEmail());
        assertEquals("Password_1234", user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void invalidEmailThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> new User("invalid-email", "Password_1234", Role.ADMIN)
        );
    }

    @Test
    void invalidPasswordThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> new User("user@test.fr", "weak", Role.ADMIN)
        );
    }

    @Test
    void nullEmailThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> new User(null, "Password_1234", Role.ADMIN)
        );
    }

    @Test
    void nullPasswordThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> new User("user@test.fr", null, Role.ADMIN)
        );
    }

    @Test
    void nullRoleThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> new User("user@test.fr", "Password_1234", null)
        );
    }

    @Test
    void emptyEmailThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> new User("", "Password_1234", Role.ADMIN)
        );
    }

    @Test
    void emptyPasswordThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> new User("user@test.fr", "", Role.ADMIN)
        );
    }

    // Tests additionnels pour la méthode canAccessAdminArea()
    @Test
    void adminUserCanAccessAdminArea() {
        User admin = new User("admin@test.fr", "Password_1234", Role.ADMIN);
        assertTrue(admin.canAccessAdminArea());
    }

    @Test
    void normalUserCannotAccessAdminArea() {
        User user = new User("user@test.fr", "Password_1234", Role.USER);
        assertFalse(user.canAccessAdminArea());
    }

    // Test pour l'email trim
    @Test
    void emailIsTrimmed() {
        User user = new User("  user@test.fr  ", "Password_1234", Role.USER);
        assertEquals("user@test.fr", user.getEmail());
    }

    // Test pour equals et hashCode (si implémentés)
    @Test
    void usersWithSameEmailAreEqual() {
        User user1 = new User("user@test.fr", "Password_1234", Role.ADMIN);
        User user2 = new User("user@test.fr", "Different_5678", Role.USER);
        
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void usersWithDifferentEmailAreNotEqual() {
        User user1 = new User("user1@test.fr", "Password_1234", Role.ADMIN);
        User user2 = new User("user2@test.fr", "Password_1234", Role.ADMIN);
        
        assertFalse(user1.equals(user2));
    }
}