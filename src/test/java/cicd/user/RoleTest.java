package cicd.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.devops.cicd.user.Role;

public class RoleTest {
    
    @Test
    void isValidRole() {
        // Test des valeurs de l'enum
        assertEquals(Role.ADMIN, Role.valueOf("ADMIN"));
        assertEquals(Role.USER, Role.valueOf("USER"));
    }
    
    @Test
    void roleValues() {
        // Vérifier que les deux rôles existent
        Role[] roles = Role.values();
        assertEquals(2, roles.length);
    }
    
    @Test
    void roleNotNull() {
        // Vérifier que les rôles ne sont pas null
        assertNotNull(Role.ADMIN);
        assertNotNull(Role.USER);
    }
    
    @Test
    void invalidRoleThrowsException() {
        // Tester qu'un rôle invalide lève une exception
        assertThrows(IllegalArgumentException.class, 
            () -> Role.valueOf("INVALID")
        );
    }
    
    @Test
    void roleToString() {
        // Vérifier les noms des rôles
        assertEquals("ADMIN", Role.ADMIN.name());
        assertEquals("USER", Role.USER.name());
    }
}