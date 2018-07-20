package com.rizzutih.service;

import com.rizzutih.model.Sku;

import java.math.BigDecimal;

public interface CheckoutService {

    /**
     * Adds SKU to checkout and calculate total price.
     * @param skus to checkout.
     */
    void addSkus(Sku... skus);

    /**
     * Calculates the total price for all sku scanned at checkout.
     * @return total price to pay at checkout.
     */
    BigDecimal calculatePrice();
}
