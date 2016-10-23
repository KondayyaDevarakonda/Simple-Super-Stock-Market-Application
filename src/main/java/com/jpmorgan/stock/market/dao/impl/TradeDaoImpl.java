package com.jpmorgan.stock.market.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jpmorgan.stock.market.dao.TradeDao;
import com.jpmorgan.stock.market.model.Stock;
import com.jpmorgan.stock.market.model.Trade;

/**
 * @version : #0
 * @author : JPMorgan Chase & Co.
 * @date : 22/10/2016
 * @description : Implementation of Trade DAO
 */

public class TradeDaoImpl implements TradeDao{

	private Map<String, List<Trade>> tradeMap = new HashMap<String, List<Trade>>();
	
	@Override
	public void addTrade(Trade trade) {
		List<Trade> trades = new ArrayList<Trade>();
	    if (tradeMap.containsKey(trade.getStock().getStockSymbol())) {
	        trades = tradeMap.get(trade.getStock().getStockSymbol());
	    }
	    trades.add(trade);
	    tradeMap.put(trade.getStock().getStockSymbol(), trades);
	}

	@Override
	public List<Trade> getTrades(Stock stock, int minutes) {
		List<Trade> result = new ArrayList<Trade>();
	    Date afterDate = getDateXMinutesEarlier(minutes);
	    List<Trade> trades = tradeMap.get(stock.getStockSymbol());
	    if (trades != null) {
	    	Iterator<Trade> it = trades.iterator();
	    	while (it.hasNext()) {
	    		Trade trade = it.next();
	    		if (trade.getTimestamp().before(afterDate)) {
	    			break;
	    		}
	    		result.add(trade);
	    	}
	    }
	    return result;
	}

	@Override
	public List<Trade> getAllTrades() {
		List<Trade> result = new ArrayList<Trade>();
	    for (String stockSymbol: tradeMap.keySet()) {
	      result.addAll(tradeMap.get(stockSymbol));
	    }
	    return result;
	}
	
	private Date getDateXMinutesEarlier(int minutes){
	    Calendar c = Calendar.getInstance();
	    c.add(Calendar.MINUTE, -minutes);
	    return c.getTime();
	  }

}
