package com.rizzutih.storage;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.rizzutih.model.Sku;

public class BasketStorageTest {

	@Before
	public void setUp() {
	}

	@Test
	public void shouldReturnOneWhenSkuAIsAddedToBasket() {
		Map<String, Integer> skuMap = new HashMap<String, Integer>();
		BasketStorage basketStorage = new BasketStorage(skuMap);
		Sku a = new Sku("a", BigDecimal.valueOf(50));
		basketStorage.addToBasket(a);
		assertEquals(Integer.valueOf(1), skuMap.get(a.getUnitName()));
	}

}
