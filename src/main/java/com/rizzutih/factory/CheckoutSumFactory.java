package com.rizzutih.factory;

import com.rizzutih.model.CheckoutSum;
import com.rizzutih.storage.BasketStorage;
import com.rizzutih.storage.SkuStorage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSumFactory {

    private BasketStorage basketStorage;

    private SkuStorage skuStorage;

    public CheckoutSumFactory(final BasketStorage basketStorage,
                              final SkuStorage skuStorage) {
        this.basketStorage = basketStorage;
        this.skuStorage = skuStorage;
    }

    /**
     * Creates a new instance of a map containing a CheckoutSum list and total price,
     * @param totalPrice is total price to pay after all sku have been checked out.
     * @return a map containing a CheckoutSum list and total price.
     */
    public Map<List<CheckoutSum>, BigDecimal> create(final BigDecimal totalPrice) {

        final Map<List<CheckoutSum>, BigDecimal> checkoutSumMap  = new HashMap<>();
        final List<CheckoutSum> checkoutSums = new ArrayList<>();
        basketStorage.getSkuQuantityMap()
                .forEach((k,v) -> checkoutSums.add(new CheckoutSum(k, v, skuStorage.get(k).getUnitPrice())));

        checkoutSumMap.put(checkoutSums, totalPrice);

        return checkoutSumMap;
    }
}
