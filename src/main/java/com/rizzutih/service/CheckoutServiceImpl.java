package com.rizzutih.service;

import com.rizzutih.calculator.PriceCalculator;
import com.rizzutih.model.Sku;
import com.rizzutih.storage.BasketStorage;
import java.math.BigDecimal;

public class CheckoutServiceImpl implements CheckoutService {

    private BasketStorage basketStorage;

    private PriceCalculator priceCalculator;

    public CheckoutServiceImpl(final BasketStorage basketStorage,
                               final PriceCalculator priceCalculator) {

        this.basketStorage = basketStorage;
        this.priceCalculator = priceCalculator;
    }

    public void addSkus(Sku... skus) {
        for (Sku sku:skus) {
            basketStorage.addToBasket(sku);
        }
    }

    @Override
    public BigDecimal calculatePrice() {
        return priceCalculator.calculate(basketStorage.getSkuQuantityMap());
    }

}
