/**
 * 
 */
package com.jpmorgan.stock.market.enums;

/**
 * @version : #0
 * @author : JPMorgan Chase & Co.
 * @date : 22/10/2016
 * @description : Constants for the stock type available
 */
public enum StockType {	
	/**
	 * Indicates that a stock is common and the dividend yield is calculated with last dividend.
	 */
	COMMON, 
	
	/**
	 * Indicates that a stock is preferred and the dividend yield is calculated with fixed dividend.
	 */
	PREFERRED;
}
