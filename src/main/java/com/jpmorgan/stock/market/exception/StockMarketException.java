package com.jpmorgan.stock.market.exception;

/**
 * @version : #0
 * @author : JPMorgan Chase & Co.
 * @date : 22/10/2016
 * @description : Throw Exception
 */

public class StockMarketException extends Exception {
	private static final long serialVersionUID = 1L;

	public StockMarketException(String message) {
		super(message);
	}
}
