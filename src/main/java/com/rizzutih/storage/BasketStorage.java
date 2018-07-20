package com.rizzutih.storage;

import java.util.Map;
import com.rizzutih.model.Sku;
import static java.util.Objects.isNull;

public class BasketStorage {
	
	Map<String, Integer> skuQuantityMap;

	public BasketStorage(Map<String, Integer> skuQuantityMap) {
		this.skuQuantityMap = skuQuantityMap;
	}

	/**
	 * Get total number of SKUs stored in the basket per SKU.
	 * @return unitName and number of SKUs stored map.
	 */
	public Map<String, Integer> getSkuQuantityMap() {
		return skuQuantityMap;
	}

	/**
	 * Adds Skus to the basket storage.
	 * @param sku object is the object about to be scan at checkout.
	 */
	public void addToBasket(final Sku sku) {
		if(isNull(skuQuantityMap.get(sku.getUnitName()))) {
			skuQuantityMap.put(sku.getUnitName(), 1);
		} else {
			skuQuantityMap.put(sku.getUnitName(), skuQuantityMap.get(sku.getUnitName()) + 1);
		}
	}
}
