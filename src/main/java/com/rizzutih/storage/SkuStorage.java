package com.rizzutih.storage;

import com.rizzutih.model.Sku;
import java.util.Map;

public class SkuStorage {

    private Map<String, Sku> skuMap;

    public SkuStorage(Map<String, Sku> skuMap) {
        this.skuMap = skuMap;
    }

    /**
     * Gets individual sku based on unitName.
     * @param unitName of the SKU.
     * @return SKU object stored based on unitName.
     */
    public Sku get(String unitName) {
        return skuMap.get(unitName);
    }

    /**
     * Adds SKU to storage.
     * @param sku
     */
    public void add(Sku sku) {
        skuMap.put(sku.getUnitName(), sku);
    }

    /**
     * Get all the SKUs in stored in stock.
     * @return unit name and SKU map.
     */
    public Map<String, Sku>  getAll() {
        return skuMap;
    }
}
