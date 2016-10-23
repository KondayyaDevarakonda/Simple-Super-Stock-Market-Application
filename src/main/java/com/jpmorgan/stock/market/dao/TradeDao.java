package com.jpmorgan.stock.market.dao;

import java.util.List;

import com.jpmorgan.stock.market.model.Stock;
import com.jpmorgan.stock.market.model.Trade;

/**
 * @version : #0
 * @author : JPMorgan Chase & Co.
 * @date : 22/10/2016
 * @description : Trade DAO for adding, getting Trade for Stock & given Minutes & getting all Trade Info
 */

public interface TradeDao {

	void addTrade(Trade trade);

	List<Trade> getTrades(Stock stock, int minutes);

	List<Trade> getAllTrades();
}
