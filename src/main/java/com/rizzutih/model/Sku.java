package com.rizzutih.model;

import java.math.BigDecimal;

public class Sku {
	
	private String unitName;
	private BigDecimal unitPrice;
	
	public Sku(String unitName, BigDecimal unitPrice) {
		this.unitName = unitName;
		this.unitPrice = unitPrice;
	}
	
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}
