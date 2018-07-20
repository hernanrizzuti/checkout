package com.rizzutih.calculator;

import com.rizzutih.model.Offer;
import com.rizzutih.model.Sku;
import com.rizzutih.storage.BasketStorage;
import com.rizzutih.storage.SkuStorage;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PriceCalculatorTest {

	private BasketStorage basketStorage;

	private SkuStorage skuStorage;

	private Sku A;

	@Before
	public void setUp() {
		basketStorage = new BasketStorage(new HashMap<>());
		skuStorage = new SkuStorage(new HashMap<>());
		A = new Sku("A", BigDecimal.valueOf(50));
	}

	@Test
	public void testCalculateReturns50WhenOneSkuIsCheckedOut() {
		skuStorage.add(A);
		basketStorage.addToBasket(A);
		PriceCalculator priceCalculator = new PriceCalculator(skuStorage);
		BigDecimal price = priceCalculator.calculate(basketStorage.getSkuQuantityMap());
		assertEquals(BigDecimal.valueOf(50), price);
	}

	@Test
	public void testCalculateReturns100WhenTwoSkuAreCheckedOut() {
		skuStorage.add(A);
		basketStorage.addToBasket(A);
		basketStorage.addToBasket(A);
		PriceCalculator priceCalculator = new PriceCalculator(skuStorage);
		BigDecimal price = priceCalculator.calculate(basketStorage.getSkuQuantityMap());
		assertEquals(BigDecimal.valueOf(100), price);
	}

	@Test
	public void testCalculateReturns130WhenThreeSkuAreCheckedOutAndOfferIsApplied() {
		A.setOffer(new Offer(3, BigDecimal.valueOf(130)));
		skuStorage.add(A);
		basketStorage.addToBasket(A);
		basketStorage.addToBasket(A);
		basketStorage.addToBasket(A);
		PriceCalculator priceCalculator = new PriceCalculator(skuStorage);
		BigDecimal price = priceCalculator.calculate(basketStorage.getSkuQuantityMap());
		assertEquals(BigDecimal.valueOf(130), price);
	}

}
