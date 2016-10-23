package com.jpmorgan.stock.market.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.stock.market.enums.StockType;
import com.jpmorgan.stock.market.enums.TradeType;
import com.jpmorgan.stock.market.model.Stock;
import com.jpmorgan.stock.market.model.Trade;
import com.jpmorgan.stock.market.service.impl.StockMarketServiceImpl;

public class StockMarketServiceImplTest {
	
	//Logger logger = Logger.getLogger(StockMarketServiceImplTest.class);
	
	private static final int DELTA = 0;
	private StockMarketService stockMarketService;
	private Stock stock1;
	private Stock stock2;
	private Stock stock3;

	@Before
	public void setup() {
		stockMarketService = new StockMarketServiceImpl();
		stock1 = new Stock("TEST", StockType.COMMON, 4, 0, 10);
		stock2 = new Stock("TEST2", StockType.PREFERRED, 3, 2, 1);
		stock3 = new Stock("TEST3", StockType.COMMON, 1, 0, 1);
	}

	// Stock Test

	@Test
	public void testAddAndGetStock() {
		stockMarketService.addStock(stock1);
		Stock result = stockMarketService.getStock(stock1.getStockSymbol());
		assertEquals(stock1, result);
	}

	@Test
	public void testCalculateDividendYield() throws Exception {
		assertEquals(1.6, stockMarketService.calculateDividendYield(stock1, 2.5), 0);
	}

	@Test
	public void testCalculateDividendYieldPrefered() throws Exception {
		assertEquals(0.8, stockMarketService.calculateDividendYield(stock2, 2.5), 0);
	}

	@Test
	public void testCalculatePERatio() throws Exception {
		assertEquals(0.63, stockMarketService.calculatePERatio(stock1, 2.5), 0);
	}

	@Test
	public void testCalculateVolumeWeightedStockPrice() throws Exception {
		List<Trade> trades = new ArrayList<Trade>();
		trades.add(new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 2));
		trades.add(new Trade(stock1, Calendar.getInstance().getTime(), 3, TradeType.BUY, 1.5));
		trades.add(new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 3));
		assertEquals(1.9, stockMarketService.calculateVolumeWeightedStockPrice(trades), 0);
	}

	@Test
	public void testCalculateGBCE() throws Exception {
		List<Trade> trades = new ArrayList<Trade>();
		trades.add(new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 2));
		trades.add(new Trade(stock2, Calendar.getInstance().getTime(), 3, TradeType.BUY, 1.5));
		trades.add(new Trade(stock3, Calendar.getInstance().getTime(), 1, TradeType.BUY, 3));
		assertEquals(2.08, stockMarketService.calculateGBCEAllShareIndex(trades), DELTA);
	}
}
