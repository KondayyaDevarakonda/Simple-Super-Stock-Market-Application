package com.jpmorgan.stock.market.dao;

import com.jpmorgan.stock.market.model.Stock;

/**
 * @version : #0
 * @author : JPMorgan Chase & Co.
 * @date : 22/10/2016
 * @description : Stock DAO for adding and getting Stock
 */

public interface StockDao {

	void addStock(Stock stock);

	Stock getStock(String symbol);
}
