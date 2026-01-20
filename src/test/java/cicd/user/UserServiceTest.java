package cicd.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.devops.cicd.user.User;
import com.devops.cicd.user.Role;
import com.devops.cicd.user.UserService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void validUserRegister() {
        // Act - Appel avec les 3 paramètres
        User user = userService.register("user@test.fr", "Password_1234", Role.ADMIN);

        // Assert
        assertNotNull(user);
        assertEquals("user@test.fr", user.getEmail());
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void registerWithInvalidEmail() {
        // Act & Assert - Doit propager l'erreur
        assertThrows(IllegalArgumentException.class, () -> {
            userService.register("invalid-email", "Password_1234", Role.ADMIN);
        });
    }

    @Test
    void registerWithInvalidPassword() {
        // Act & Assert - Mot de passe trop faible
        assertThrows(IllegalArgumentException.class, () -> {
            userService.register("user@test.fr", "weak", Role.ADMIN);
        });
    }

}