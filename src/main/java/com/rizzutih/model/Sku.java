package com.rizzutih.model;

import java.math.BigDecimal;

/**
 * Sku (stock keeping unit) holds data about unit name, unit price and offers that applies to this particular object.
 */
public class Sku {
	
	private String unitName;

	private BigDecimal unitPrice;

	private Offer offer;
	
	public Sku(String unitName, BigDecimal unitPrice) {
		this.unitName = unitName;
		this.unitPrice = unitPrice;
	}

	public String getUnitName() {
		return unitName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

}
