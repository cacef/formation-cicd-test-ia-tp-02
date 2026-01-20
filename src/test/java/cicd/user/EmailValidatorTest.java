package cicd.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.devops.cicd.user.EmailValidator;

public class EmailValidatorTest {

    @Test
    void validEmails() {
        // Exemples valides de la spec
        assertTrue(EmailValidator.isValid("alice@test.com"));
        assertTrue(EmailValidator.isValid("bob.smith@company.io"));
    }

    @Test
    void invalidEmails() {
        // Exemples invalides de la spec
        assertFalse(EmailValidator.isValid(""));
        assertFalse(EmailValidator.isValid(" "));
        assertFalse(EmailValidator.isValid("alice"));
        assertFalse(EmailValidator.isValid("alice@"));
        assertFalse(EmailValidator.isValid("@test.com"));
        assertFalse(EmailValidator.isValid("alice@test"));
        assertFalse(EmailValidator.isValid("alice@@test.com"));
    }

    @Test
    void nullEmail() {
        // Obligatoire (non null)
        assertFalse(EmailValidator.isValid(null));
    }

    @Test
    void emailWithWhitespace() {
        // Non vide après trim
        assertFalse(EmailValidator.isValid("   "));
        assertFalse(EmailValidator.isValid("\t"));
        assertFalse(EmailValidator.isValid("\n"));
    }

    @Test
    void emailWithoutAt() {
        // Doit contenir exactement un @
        assertFalse(EmailValidator.isValid("alicetest.com"));
        assertFalse(EmailValidator.isValid("alice"));
    }

    @Test
    void emailWithMultipleAt() {
        // Exactement un seul @
        assertFalse(EmailValidator.isValid("alice@@test.com"));
        assertFalse(EmailValidator.isValid("alice@test@com"));
    }

    @Test
    void emailWithoutDotAfterAt() {
        // Doit contenir au moins un . après le @
        assertFalse(EmailValidator.isValid("alice@test"));
        assertFalse(EmailValidator.isValid("alice@testcom"));
    }

    @Test
    void emailWithDotBeforeAt() {
        // Le . doit être APRÈS le @
        assertTrue(EmailValidator.isValid("bob.smith@company.io"));
        assertFalse(EmailValidator.isValid("bob.smith@company"));
    }
}