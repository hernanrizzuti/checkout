package com.rizzutih.calculator;

import com.rizzutih.model.Sku;
import com.rizzutih.storage.SkuStorage;
import java.math.BigDecimal;
import java.util.Map;
import static java.util.Objects.isNull;

public class PriceCalculator {

    private SkuStorage skuStorage;

    public PriceCalculator(final SkuStorage skuStorage) {
        this.skuStorage =  skuStorage;
    }

    /**
     * Calculates the total price at checkout and applies offers
     * @param skuQuantityMap is a map containing the quantity per sku checked out.
     * @return total price to pay at checkout.
     */
    public BigDecimal calculate(final Map<String, Integer> skuQuantityMap) {

        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal tmpPrice = BigDecimal.ZERO;

        for (Map.Entry<String, Integer> entry : skuQuantityMap.entrySet()) {
            final String skuUnitName = entry.getKey();
            final Integer skuQuantity = entry.getValue();
            final Sku sku = skuStorage.get(skuUnitName);

            int count = 0;

            for (int i = 1; i <= skuQuantity; i++) {
                if (isNull(sku.getOffer())) {
                    totalPrice = totalPrice.add(sku.getUnitPrice());
                } else {
                    count++;
                    if (count == sku.getOffer().getQuantity()) {
                        totalPrice = totalPrice.add(sku.getOffer().getPrice());
                        count = 0;
                        tmpPrice = BigDecimal.ZERO;
                    } else {
                        tmpPrice = tmpPrice.add(sku.getUnitPrice());
                    }
                }
            }
            totalPrice = totalPrice.add(tmpPrice);
            tmpPrice = BigDecimal.ZERO;
        }

        return totalPrice;
    }
}
