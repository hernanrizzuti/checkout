package com.rizzutih.model;

import java.math.BigDecimal;

/**
 * Offer holds data about quantity and offer price for a particular SKU.
 */
public class Offer {

    private int quantity;

    private BigDecimal price;

    public Offer(final int quantity, final BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
