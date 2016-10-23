package com.jpmorgan.stock.market.model;

import java.util.Date;

import com.jpmorgan.stock.market.enums.TradeType;

/**
 * @version : #0
 * @author : JPMorgan Chase & Co.
 * @date : 22/10/2016
 * @description : Trade Model
 */

public class Trade {

	private Stock stock;
	private TradeType tradeType;
	private long shareQuantity;
	private double price;
	private Date timestamp;

	public Trade(Stock stock, Date timestamp, int shareQuantity, TradeType tradeType, double price) {
	    super();
	    this.stock = stock;
	    this.timestamp = timestamp;
	    this.shareQuantity = shareQuantity;
	    this.tradeType = tradeType;
	    this.price = price;
	  }

	  public Stock getStock() {
	    return stock;
	  }

	  public void setStock(Stock stock) {
	    this.stock = stock;
	  }

	  public Date getTimestamp() {
	    return timestamp;
	  }

	  public void setTimestamp(Date timestamp) {
	    this.timestamp = timestamp;
	  }

	  public long getShareQuantity() {
	    return shareQuantity;
	  }

	  public void setShareQuantity(long shareQuantity) {
	    this.shareQuantity = shareQuantity;
	  }

	  public TradeType getTradeType() {
	    return tradeType;
	  }

	  public void setTradeType(TradeType tradeType) {
	    this.tradeType = tradeType;
	  }

	  public double getPrice() {
	    return price;
	  }

	  public void setPrice(double price) {
	    this.price = price;
	  }

	  public int compareTo(Trade trade) {
	    return trade.getTimestamp().compareTo(this.timestamp);
	  }
}
