package com.rizzutih.controller;

import com.rizzutih.model.CheckoutSum;
import com.rizzutih.factory.CheckoutSumFactory;
import com.rizzutih.model.Sku;
import com.rizzutih.service.CheckoutServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CheckoutController {

    private CheckoutServiceImpl checkoutService;

    private CheckoutSumFactory checkoutSumFactory;

    public CheckoutController(final CheckoutServiceImpl checkoutService,
                              final CheckoutSumFactory checkoutSumFactory) {
        this.checkoutService = checkoutService;
        this.checkoutSumFactory = checkoutSumFactory;
    }

    /**
     * Receives SKUs and process them by adding them to the basket storage,
     * calculating the total price and returning a checkout summary
     * @param skus scanned objects at checkout
     * @return checkout sum map
     */
    public Map<List<CheckoutSum>,BigDecimal> checkout(final Sku...skus) {

        checkoutService.addSkus(skus);

        return checkoutSumFactory.create(checkoutService.calculatePrice());
    }
}
