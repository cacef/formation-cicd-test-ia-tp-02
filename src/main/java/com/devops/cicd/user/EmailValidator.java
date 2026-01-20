package com.devops.cicd.user;

public final class EmailValidator {

    private EmailValidator() {}

    public static boolean isValid(String email) {
        if (email == null) {
            return false;
        }
        
        String trimmed = email.trim();
        if (trimmed.isEmpty()) {
            return false;
        }
        
        int firstAt = trimmed.indexOf('@');
        int lastAt = trimmed.lastIndexOf('@');
        
        if (firstAt == -1 || firstAt != lastAt) {
            return false;
        }
        
        if (firstAt == 0 || firstAt == trimmed.length() - 1) {
            return false;
        }
        
        String afterAt = trimmed.substring(firstAt + 1);
        return afterAt.contains(".");
    }
}