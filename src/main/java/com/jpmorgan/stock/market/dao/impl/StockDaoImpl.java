package com.jpmorgan.stock.market.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.jpmorgan.stock.market.dao.StockDao;
import com.jpmorgan.stock.market.model.Stock;

/**
 * @version : #0
 * @author : JPMorgan Chase & Co.
 * @date : 22/10/2016
 * @description : Implementation of Stock DAO
 */

public class StockDaoImpl implements StockDao {
	
	private Map<String, Stock> stockMap = new HashMap<String, Stock>();

	@Override
	public void addStock(Stock stock) {
		stockMap.put(stock.getStockSymbol(), stock);
	}

	@Override
	public Stock getStock(String stockSymbol) {
		return stockMap.get(stockSymbol);
	}

}
