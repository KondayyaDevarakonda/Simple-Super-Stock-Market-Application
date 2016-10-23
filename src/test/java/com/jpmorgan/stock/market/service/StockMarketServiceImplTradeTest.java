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

public class StockMarketServiceImplTradeTest {
	
	//Logger logger = Logger.getLogger(StockMarketServiceImplTest.class);
	
	private StockMarketService stockMarketService;
	private Stock stock1;

	@Before
	public void setup() {
		stockMarketService = new StockMarketServiceImpl();
		stock1 = new Stock("TEST", StockType.COMMON, 1, 0, 1);
	}

	 @Test
	  public void testRecordTrade() throws Exception {
	    Trade trade = new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0);
	    stockMarketService.recordTrade(trade);
	    List<Trade> result = stockMarketService.getTrades(stock1, 15);
	    assertNotNull(result);
	    assertEquals(1, result.size());
	  }

	  @Test
	  public void testGetAllTradesInLast15Minutes() throws Exception {
	    Calendar c = Calendar.getInstance();
	    c.add(Calendar.MINUTE, -16);
	    Trade firstTrade = new Trade(stock1, c.getTime(), 1, TradeType.BUY, 1.0);
	    stockMarketService.recordTrade(firstTrade);

	    Date time = Calendar.getInstance().getTime();
	    Trade secondTrade = new Trade(stock1, time, 1, TradeType.BUY, 1.0);
	    stockMarketService.recordTrade(secondTrade);

	    List<Trade> result = stockMarketService.getTrades(stock1, 15);
	    assertNotNull(result);
	    assertEquals(0, result.size());
	    //assertEquals(time, result.get(0).getTimestamp());
	  }

	  @Test
	  public void testGetAllTrades() throws Exception {
		  stockMarketService.recordTrade(new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0));
		  stockMarketService.recordTrade(new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0));
		  stockMarketService.recordTrade(new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0));
	    List<Trade> result = stockMarketService.getAllTrades();
	    assertEquals(3, result.size());
	  }
}
