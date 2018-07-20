package com.rizzutih.service;

import com.rizzutih.calculator.PriceCalculator;
import com.rizzutih.model.Offer;
import com.rizzutih.model.Sku;
import com.rizzutih.storage.BasketStorage;
import com.rizzutih.storage.SkuStorage;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import static org.junit.Assert.*;

public class CheckoutServiceImplTest {

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
    }

    @Test
    public void testAddSkuAddsSkusWhenMultipleSkuArePassedIn() {
        PriceCalculator priceCalculator = new PriceCalculator(skuStorage);
        CheckoutServiceImpl checkoutService = new CheckoutServiceImpl(basketStorage, priceCalculator);
        checkoutService.addSkus(A,B,A,C,A,D,B);
        assertEquals(4, basketStorage.getSkuQuantityMap().size());
    }

    @Test
    public void testCalculateTotalPriceReturns260WhenFourATwoBOneCOneDAreCheckedOut() {
        skuStorage.add(A);
        skuStorage.add(B);
        skuStorage.add(C);
        skuStorage.add(D);
        PriceCalculator priceCalculator = new PriceCalculator(skuStorage);
        CheckoutServiceImpl checkoutService = new CheckoutServiceImpl(basketStorage, priceCalculator);
        checkoutService.addSkus(A,B,A,C,A,A,D,B);
        assertEquals(BigDecimal.valueOf(260), checkoutService.calculatePrice());
    }

}