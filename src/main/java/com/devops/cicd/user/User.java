package com.devops.cicd.user;

import com.devops.cicd.PasswordPolicy;

final public class User {

    private final String email;
    private final String password;
    private final Role role;

    public User(String email, String password, Role role) {
        // Validation du role (doit être fait en premier car non null requis)
        if (role == null) {
            throw new IllegalArgumentException("role must not be null");
        }

        // Validation de l'email
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("email must be valid");
        }
        String trimmedEmail = email.trim();
        if (!EmailValidator.isValid(trimmedEmail)) {
            throw new IllegalArgumentException("email must be valid");
        }

        // Validation du password
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("password must be strong");
        }
        if (!PasswordPolicy.isStrong(password)) {
            throw new IllegalArgumentException("password must be strong");
        }

        // Assignation des valeurs validées
        this.email = trimmedEmail;  // email normalisé (trim)
        this.password = password;    // password non modifié
        this.role = role;            // role non null
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean canAccessAdminArea() {
        return role == Role.ADMIN;
    }

    // BONUS: equals/hashCode/toString
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "User{email='" + email + "', role=" + role + "}";
    }
}