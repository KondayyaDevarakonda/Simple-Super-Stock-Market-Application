package com.jpmorgan.stock.market.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.jpmorgan.stock.market.dao.StockDao;
import com.jpmorgan.stock.market.dao.TradeDao;
import com.jpmorgan.stock.market.dao.impl.StockDaoImpl;
import com.jpmorgan.stock.market.dao.impl.TradeDaoImpl;
import com.jpmorgan.stock.market.enums.StockType;
import com.jpmorgan.stock.market.exception.StockMarketException;
import com.jpmorgan.stock.market.model.Stock;
import com.jpmorgan.stock.market.model.Trade;
import com.jpmorgan.stock.market.service.StockMarketService;

/**
 * @version : <Version No>
 * @author : <Company Name>
 * @date : <Date code added or modified>
 * @description : Stock Model
 */

public class StockMarketServiceImpl implements StockMarketService {

	private Logger logger = Logger.getLogger(StockMarketServiceImpl.class);

	private static StockMarketServiceImpl instance = null;

	private StockDao stockDao = new StockDaoImpl();

	private TradeDao tradeDao = new TradeDaoImpl();

	public static StockMarketServiceImpl getInstance() {
		if (instance == null) {
			instance = new StockMarketServiceImpl();
		}
		return instance;
	}

	@Override
	public void addStock(Stock stock) {
		getStockDao().addStock(stock);
	}

	@Override
	public Stock getStock(String symbol) {
		return getStockDao().getStock(symbol);
	}

	@Override
	public double calculateDividendYield(Stock stock, double price) throws Exception {
		double result = 0;
		try {
			if (StockType.PREFERRED.equals(stock.getStockType())) {
				return (stock.getFixedDividend() * stock.getParValue()) / price;
			}
			result = stock.getLastDividend() / price;
		} catch (Exception exception) {
			throw new Exception("Error calculate Dividend Yield " + exception.getMessage());
		}
		return round(result, 2);
	}

	@Override
	public double calculatePERatio(Stock stock, double price) throws Exception {
		double result = 0;
		try {
			result = price / stock.getLastDividend();
		} catch (Exception exception) {
			throw new Exception("Error calculate PE Ratio: " + exception.getMessage());
		}
		return round(result, 2);
	}

	@Override
	public double calculateVolumeWeightedStockPrice(List<Trade> trades) throws Exception {
		double sumOfPriceQuantity = 0;
		long sumOfQuantity = 0;
		double result = 0;

		try {
			for (Trade trade : trades) {
				sumOfPriceQuantity = sumOfPriceQuantity + (trade.getPrice() * trade.getShareQuantity());
				sumOfQuantity = sumOfQuantity + trade.getShareQuantity();
			}
			result = sumOfPriceQuantity / sumOfQuantity;
		} catch (Exception exception) {
			throw new Exception("Error calculate Volume Weighted Stock Price: " + exception.getMessage());
		}
		return round(result, 2);
	}

	@Override
	public double calculateGBCEAllShareIndex(List<Trade> trades) throws Exception {
		double total = 1;
		double result = 0;
		try {
			for (Trade trade : trades) {
				total = total * trade.getPrice();
			}
			result = Math.pow(total, (1D / trades.size()));
		} catch (Exception exception) {
			throw new Exception("Error calculating GBCE All Share Index: " + exception.getMessage());
		}
		return round(result, 2);
	}

	@Override
	public void recordTrade(Trade trade) {
		if (trade != null && trade.getStock() != null) {
			getTradeDao().addTrade(trade);
		}
	}

	@Override
	public List<Trade> getTrades(Stock stock, int numberOfMinutes) {
		return getTradeDao().getTrades(stock, numberOfMinutes);
	}

	@Override
	public List<Trade> getAllTrades() {
		return getTradeDao().getAllTrades();
	}

	/**
	 * Load Stock Symbols
	 */
	@Override
	public Map<Long, String> getStockSymbol() {
		Map<Long, String> stockSymbol = new HashMap<Long, String>();
		stockSymbol.put(1L, "TEA");
		stockSymbol.put(2L, "POP");
		stockSymbol.put(3L, "ALE");
		stockSymbol.put(4L, "GIN");
		stockSymbol.put(5L, "JOE");

		return stockSymbol;
	}

	/**
	 * Round up to number of places
	 * 
	 * @param value
	 * @param places
	 * @return
	 */
	private static double round(double value, int places) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public StockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	public TradeDao getTradeDao() {
		return tradeDao;
	}

	public void setTradeDao(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}
}
