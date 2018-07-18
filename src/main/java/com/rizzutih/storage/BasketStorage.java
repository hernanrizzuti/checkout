package com.rizzutih.storage;

import java.util.Map;

import com.rizzutih.model.Sku;

public class BasketStorage {
	
	Map<String, Integer> skuMap;

	public BasketStorage(Map<String, Integer> skuMap) {
		this.skuMap = skuMap;
	}

	public Map<String, Integer> getSkuMap() {
		return skuMap;
	}

	public void addToBasket(final Sku sku) {
		skuMap.put(sku.getUnitName(), 1);
	}
	
}
