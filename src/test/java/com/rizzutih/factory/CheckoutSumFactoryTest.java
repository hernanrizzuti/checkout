package com.rizzutih.factory;

import com.rizzutih.model.CheckoutSum;
import com.rizzutih.model.Sku;
import com.rizzutih.storage.BasketStorage;
import com.rizzutih.storage.SkuStorage;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CheckoutSumFactoryTest {

    @Test
    public void testCreateReturnsANewCheckoutSumInstance() {
        final BasketStorage basketStorage = new BasketStorage(new HashMap<>());
        final SkuStorage skuStorage = new SkuStorage(new HashMap<>());
        final String expectedUnitName ="A";
        final BigDecimal expectedUnitPrice = BigDecimal.valueOf(50);
        final BigDecimal expectedTotalPrice = BigDecimal.valueOf(175);
        final Sku A = new Sku(expectedUnitName, expectedUnitPrice);
        basketStorage.addToBasket(A);
        skuStorage.add(A);
        final CheckoutSumFactory checkoutSumFactory = new CheckoutSumFactory(basketStorage, skuStorage);
        final Map<List<CheckoutSum>, BigDecimal> checkoutSum = checkoutSumFactory.create(expectedTotalPrice);
        final List<CheckoutSum> checkoutSums = checkoutSum.entrySet().stream().findFirst().get().getKey();
        final BigDecimal totalPrice = checkoutSum.entrySet().stream().findFirst().get().getValue();
        assertEquals(expectedUnitName, checkoutSums.get(0).getUnitName());
        assertEquals(expectedUnitPrice, checkoutSums.get(0).getUnitPrice());
        assertEquals(Integer.valueOf(1), checkoutSums.get(0).getQuantity());
        assertEquals(expectedTotalPrice, totalPrice);

    }
}