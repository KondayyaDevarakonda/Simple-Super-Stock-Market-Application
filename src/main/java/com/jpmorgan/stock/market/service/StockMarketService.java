package com.jpmorgan.stock.market.service;

import java.util.List;
import java.util.Map;

import com.jpmorgan.stock.market.model.Stock;
import com.jpmorgan.stock.market.model.Trade;

/**
 * @version : #0
 * @author : JPMorgan Chase & Co.
 * @date : 22/10/2016
 * @description : Trade Model
 */

public interface StockMarketService {

	/**
	 * Add a {@code Stock}
	 * 
	 * @param stock
	 */
	void addStock(Stock stock);

	/**
	 * Get a {@code Stock}
	 * 
	 * @param symbol
	 * @return
	 */
	Stock getStock(String symbol);

	/**
	 * Calculate the dividend yield for an {@code Stock} and price
	 * 
	 * @param stock
	 * @param price
	 * @return
	 */
	double calculateDividendYield(Stock stock, double price) throws Exception;

	/**
	 * Calculate the P/E ration for an {@code Stock} and price
	 * 
	 * @param stock
	 * @param price
	 * @return
	 */
	double calculatePERatio(Stock stock, double price) throws Exception;

	/**
	 * Calculate the volume weighted stock price based on a list of
	 * {@code Trades}
	 * 
	 * @param trades
	 * @return
	 */
	double calculateVolumeWeightedStockPrice(List<Trade> trades) throws Exception;

	/**
	 * Calculate the GBCE for a list of {@code Trades}
	 * 
	 * @param trades
	 * @return
	 */
	double calculateGBCEAllShareIndex(List<Trade> trades) throws Exception;

	/**
	 * Record a {@code Trade}
	 * 
	 * @param trade
	 */
	void recordTrade(Trade trade) throws Exception;

	/**
	 * Get a list of {@code Trade}s for {@code Stock} within the last x minutes
	 * 
	 * @param stock
	 * @param numberOfMinutes
	 * @return
	 */
	List<Trade> getTrades(Stock stock, int numberOfMinutes);

	/**
	 * Get all {@code Trade}s
	 * 
	 * @return
	 */
	List<Trade> getAllTrades();

	/**
	 * Get all {@code Stock Stock}s
	 * 
	 * @return
	 */
	Map<Long, String> getStockSymbol();
}
