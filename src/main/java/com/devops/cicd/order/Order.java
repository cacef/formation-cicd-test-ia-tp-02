package com.devops.cicd.order;

/**
 * Représente une commande client.
 *
 * Attributs attendus :
 * - id : identifiant unique (non null, non vide)
 * - quantity : nombre d'articles (> 0)
 * - unitPrice : prix unitaire (> 0)
 * - priority : commande prioritaire ou non
 */
public final class Order {

    private final String id;
    private final int quantity; 
    private final double unitPrice;
    private final boolean priority;

    public Order(String id, int quantity, double unitPrice, boolean priority) {
        // Validation de l'id (non null, non vide)
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("id must not be null or empty");
        }

        // Validation de quantity (> 0)
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }

        // Validation de unitPrice (> 0)
        if (unitPrice <= 0) {
            throw new IllegalArgumentException("unitPrice must be greater than 0");
        }

        this.id = id.trim();
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public boolean isPriority() {
        return priority;
    }

    public double getTotalPrice() {
        return quantity * unitPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Order order = (Order) obj;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', quantity=" + quantity + 
               ", unitPrice=" + unitPrice + ", priority=" + priority + "}";
    }
}