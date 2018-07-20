package com.rizzutih.model;

import java.math.BigDecimal;

/**
 *  CheckoutSum object gives a summary of unit names, unit prices and quantity.
 */
public class CheckoutSum {

    private String unitName;

    private Integer quantity;

    private BigDecimal unitPrice;

    public CheckoutSum(String unitName, Integer quantity, BigDecimal unitPrice) {
        this.unitName = unitName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getUnitName() {
        return unitName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

}
