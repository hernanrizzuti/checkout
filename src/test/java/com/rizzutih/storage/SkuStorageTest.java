package com.rizzutih.storage;

import com.rizzutih.model.Offer;
import com.rizzutih.model.Sku;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class SkuStorageTest {

    private Map<String, Sku> skuMap;

    private SkuStorage skuStorage;

    @Before
    public void setUp() {
        skuMap = new HashMap<>();
        skuStorage = new SkuStorage(skuMap);
    }

    @Test
    public void testAddAddsOneSkuToStorageWhenSkuAIsPassedIn() {
        Sku a = new Sku("a", BigDecimal.valueOf(50));
        skuStorage.add(a);
        assertEquals(a, skuMap.get(a.getUnitName()));
    }

    @Test
    public void testGetReturnsSkuWhenAdded() {
        Sku a = new Sku("a", BigDecimal.valueOf(50));
        skuStorage.add(a);
        assertEquals(a, skuStorage.get(a.getUnitName()));
    }

    @Test
    public void testGetAllReturnsTwoWhenTwoSkusWereAdded() {
        Sku a = new Sku("a", BigDecimal.valueOf(50));
        a.setOffer(new Offer(3, new BigDecimal(130)));
        Sku b = new Sku("b", BigDecimal.valueOf(45));
        skuStorage.add(a);
        skuStorage.add(b);
        assertEquals(2, skuStorage.getAll().size());
    }

}
