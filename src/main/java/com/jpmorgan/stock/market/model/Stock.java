package com.jpmorgan.stock.market.model;

import com.jpmorgan.stock.market.enums.StockType;

/**
 * @version : #0
 * @author : JPMorgan Chase & Co.
 * @date : 22/10/2016
 * @description : Stock Model
 */

public class Stock {

	private String stockSymbol;
	private StockType stockType;
	private double lastDividend;
	private double fixedDividend;
	private double parValue;
	
	public Stock(String stockSymbol, StockType stockType, double lastDividend, 
			double fixedDividend, double parValue) {
		super();
		this.setStockSymbol(stockSymbol);
		this.setStockType(stockType);
		this.setLastDividend(lastDividend);
		this.setFixedDividend(fixedDividend);
		this.setParValue(parValue);
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	public double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}
}