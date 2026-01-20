package com.devops.cicd.order;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Service de calcul du montant total d'une commande.
 *
 * Règles métier :
 * 1) Valider la commande
 * 2) Calculer le sous-total : quantity * unitPrice
 * 3) Si le sous-total >= 100 €, appliquer une remise de 5 %
 * 4) Si la commande est prioritaire, ajouter 9.99 € de frais
 * 5) Arrondir le total final à 2 décimales
 */
public class OrderService {

    public static final double PRIORITY_FEE = 9.99;
    public static final double DISCOUNT_THRESHOLD = 100.0;
    public static final double DISCOUNT_RATE = 0.05;

    public double computeTotal(Order order) {
        // 1) Valider la commande
        if (order == null) {
            throw new IllegalArgumentException("order must not be null");
        }

        // 2) Calculer le sous-total : quantity * unitPrice
        double subtotal = order.getQuantity() * order.getUnitPrice();

        // 3) Si le sous-total >= 100 €, appliquer une remise de 5 %
        double total = subtotal;
        if (subtotal >= DISCOUNT_THRESHOLD) {
            total = subtotal * (1 - DISCOUNT_RATE);
        }

        // 4) Si la commande est prioritaire, ajouter 9.99 € de frais
        if (order.isPriority()) {
            total += PRIORITY_FEE;
        }

        // 5) Arrondir le total final à 2 décimales
        return roundToTwoDecimals(total);
    }

    private double roundToTwoDecimals(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}