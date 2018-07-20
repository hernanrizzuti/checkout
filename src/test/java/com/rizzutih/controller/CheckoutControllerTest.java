package com.rizzutih.controller;

import com.rizzutih.calculator.PriceCalculator;
import com.rizzutih.model.CheckoutSum;
import com.rizzutih.factory.CheckoutSumFactory;
import com.rizzutih.model.Offer;
import com.rizzutih.model.Sku;
import com.rizzutih.service.CheckoutServiceImpl;
import com.rizzutih.storage.BasketStorage;
import com.rizzutih.storage.SkuStorage;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CheckoutControllerTest {

    private BasketStorage basketStorage;

    private SkuStorage skuStorage;

    private Sku A, B, C, D;

    @Before
    public void setUp() {
        basketStorage = new BasketStorage(new HashMap<>());
        skuStorage = new SkuStorage(new HashMap<>());
        A = new Sku("A", BigDecimal.valueOf(50));
        A.setOffer(new Offer(3, BigDecimal.valueOf(130)));
        B = new Sku("B", BigDecimal.valueOf(30));
        B.setOffer(new Offer(2, BigDecimal.valueOf(45)));
        C = new Sku("C", BigDecimal.valueOf(20));
        D = new Sku("D", BigDecimal.valueOf(15));
        skuStorage.add(A);
        skuStorage.add(B);
        skuStorage.add(C);
        skuStorage.add(D);
    }

    @Test
    public void testCheckoutReturnCheckoutSummary() {
        PriceCalculator priceCalculator = new PriceCalculator(skuStorage);
        CheckoutServiceImpl checkoutService = new CheckoutServiceImpl(basketStorage, priceCalculator);
        CheckoutSumFactory checkoutSumFactory = new CheckoutSumFactory(basketStorage, skuStorage);
        CheckoutController checkoutController = new CheckoutController(checkoutService, checkoutSumFactory);
        Map<List<CheckoutSum>, BigDecimal> checkoutSum = checkoutController.checkout(A, B, A, A, C, B, B, C, D);
        assertNotNull(checkoutSum);

        CheckoutSum checkoutSumA = checkoutSum.entrySet().stream().findFirst().get().getKey().get(0);
        assertCheckSum("A", BigDecimal.valueOf(50), Integer.valueOf(3), checkoutSumA);

        CheckoutSum checkoutSumB = checkoutSum.entrySet().stream().findFirst().get().getKey().get(1);
        assertCheckSum("B",BigDecimal.valueOf(30), Integer.valueOf(3), checkoutSumB);

        CheckoutSum checkoutSumC = checkoutSum.entrySet().stream().findFirst().get().getKey().get(2);
        assertCheckSum("C", BigDecimal.valueOf(20), Integer.valueOf(2), checkoutSumC);

        CheckoutSum checkoutSumD = checkoutSum.entrySet().stream().findFirst().get().getKey().get(3);
        assertCheckSum("D", BigDecimal.valueOf(15), Integer.valueOf(1), checkoutSumD);
        assertEquals(BigDecimal.valueOf(260), checkoutSum.entrySet().stream().findFirst().get().getValue());

    }

    private void assertCheckSum(final String unitName,
                                final BigDecimal unitPrice,
                                final Integer quantity,
                                final CheckoutSum checkoutSum) {
        assertEquals(unitName, checkoutSum.getUnitName());
        assertEquals(unitPrice, checkoutSum.getUnitPrice());
        assertEquals(quantity, checkoutSum.getQuantity());
    }

}