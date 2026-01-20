package com.devops.cicd.order;

/**
 * Classe utilitaire de validation des commandes.
 *
 * Règles de validation :
 * - la commande ne doit pas être null
 * - id ne doit pas être null ni vide
 * - quantity doit être strictement positive
 * - unitPrice doit être strictement positif
 *
 * En cas d'erreur, lever IllegalArgumentException.
 */
public final class OrderValidator {

    private OrderValidator() {}

    public static void validate(Order order) {
        // La commande ne doit pas être null
        if (order == null) {
            throw new IllegalArgumentException("order must not be null");
        }

        // id ne doit pas être null ni vide
        if (order.getId() == null || order.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("id must not be null or empty");
        }

        // quantity doit être strictement positive
        if (order.getQuantity() <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }

        // unitPrice doit être strictement positif
        if (order.getUnitPrice() <= 0) {
            throw new IllegalArgumentException("unitPrice must be greater than 0");
        }
    }
}