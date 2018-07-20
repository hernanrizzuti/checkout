package com.rizzutih.storage;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import com.rizzutih.model.Sku;

public class BasketStorageTest {

	private Map<String, Integer> skuQuantityMap;

	private BasketStorage basketStorage;

	@Before
	public void setUp() {
		skuQuantityMap = new HashMap<>();
		basketStorage = new BasketStorage(skuQuantityMap);
	}

	@Test
	public void testAddToBasketReturnsOneWhenOneSkuAIsAddedToBasket() {
		Sku a = new Sku("a", BigDecimal.valueOf(50));
		basketStorage.addToBasket(a);
		assertEquals(Integer.valueOf(1), skuQuantityMap.get(a.getUnitName()));
	}

	@Test
	public void testAddToBasketReturnsTwoWhenTwoSkuAAreAddedToBasket() {
		Sku a = new Sku("a", BigDecimal.valueOf(50));
		basketStorage.addToBasket(a);
		basketStorage.addToBasket(a);
		assertEquals(Integer.valueOf(2), skuQuantityMap.get(a.getUnitName()));
	}

	@Test
	public void testGetSkuQuantityMapToBasketReturnsMap() {
		assertEquals(skuQuantityMap, basketStorage.getSkuQuantityMap());
	}

}
