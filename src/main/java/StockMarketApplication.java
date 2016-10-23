import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.jpmorgan.stock.market.enums.StockType;
import com.jpmorgan.stock.market.enums.TradeType;
import com.jpmorgan.stock.market.exception.StockMarketException;
import com.jpmorgan.stock.market.model.Stock;
import com.jpmorgan.stock.market.model.Trade;
import com.jpmorgan.stock.market.service.StockMarketService;
import com.jpmorgan.stock.market.service.impl.StockMarketServiceImpl;

public class StockMarketApplication {
	
	private static StockMarketService stockMarketService = StockMarketServiceImpl.getInstance();
	
	private static Scanner scanner;
	
	public static void main(String[] args) throws Exception {
	    initStocks();
	    printMenu();
	    
	    scanner = new Scanner(System.in);
	    String choice= null;
	    while (true) {
	      choice = scanner.nextLine();
	      if ("q".equals(choice)) {
	        scanner.close();
	        System.exit(0);
	      } else {
	        try {
	          int option = Integer.parseInt(choice);
	          Stock stockFromUser;
	          double priceFromUser;

	          switch (option) {
	            case 1:
	              stockFromUser = getStockFromUser();
	              priceFromUser = getStockPriceFromUser();
	              calculateDividendYield(stockFromUser, priceFromUser);
	              break;
	            case 2:
	              stockFromUser = getStockFromUser();
	              priceFromUser = getStockPriceFromUser();
	              calculatePERatio(stockFromUser, priceFromUser);
	              break;
	            case 3:
	              stockFromUser = getStockFromUser();
	              int quantityFromUser = getQuantityFromUser();
	              TradeType tradeTypeFromUser = getTradeType();
	              priceFromUser = getStockPriceFromUser();
	              recordTrade(stockFromUser, quantityFromUser, tradeTypeFromUser, priceFromUser);
	              break;
	            case 4:
	              stockFromUser = getStockFromUser();
	              calculateVolumeWeightedStockPrice(stockFromUser);
	              break;
	            case 5:
	              calculateGBCE();
	              break;
	            default:
	              break;
	          }
	        } catch (NumberFormatException e) {
	          printResult("Invalid Option");
	        } catch (StockMarketException e1) {
	          printResult(e1.getMessage());
	        }
	        System.out.println("");
	        printMenu();
	      }
	    }
	  }

	  private static Stock getStockFromUser() throws StockMarketException {
	    System.out.println("Please input stock symbol");
	    String stockSymbol = scanner.nextLine();
	    Stock stock = stockMarketService.getStock(stockSymbol);
	    if (stock == null) {
	      throw new StockMarketException("Stock not found");
	    }
	    return stock;
	  }

	  private static double getStockPriceFromUser() throws StockMarketException {
	    System.out.println("Please input stock price");
	    String stockPrice = scanner.nextLine();
	    try {
	      double result = Double.parseDouble(stockPrice);
	      if (result <= 0) {
	        throw new StockMarketException("Invalid price: Must be greated than 0");
	      }
	      return result;
	    } catch (NumberFormatException e) {
	      throw new StockMarketException("Invalid price: Not a number");
	    }
	  }

	  private static TradeType getTradeType() throws StockMarketException {
	    System.out.println("Please input trade type (BUY/SELL)");
	    String type = scanner.nextLine();
	    try {
	      return TradeType.valueOf(type.toUpperCase());
	    } catch (IllegalArgumentException e) {
	      throw new StockMarketException("Invalid trade type: Must be BUY or SELL");
	    }
	  }

	  private static int getQuantityFromUser() throws StockMarketException {
	    System.out.println("Please input quantity");
	    String quantity = scanner.nextLine();
	    try {
	      int result = Integer.parseInt(quantity);
	      if (result <= 0) {
	        throw new StockMarketException("Invalid quantity: Must be greated than 0");
	      }
	      return result;
	    } catch (NumberFormatException e) {
	      throw new StockMarketException("Invalid quantity: Not a number");
	    }
	  }

	  private static void printMenu() {
	    System.out.println("JPMorgan - Super simple stock market");
	    System.out.println("1: Calculate dividend yield for stock");
	    System.out.println("2: Calculate P/E ratio for stock");
	    System.out.println("3: Record a trade for stock");
	    System.out.println("4: Calculate Volume Weighted Stock Price for stock");
	    System.out.println("5: Calculate GBCE All Share Index");
	    System.out.println("q: Quit");
	  }

	  private static void calculateDividendYield(Stock stock, double price) throws Exception {
	    double result = stockMarketService.calculateDividendYield(stock, price);
	    printResult("Dividend Yield: " + result);
	  }

	  private static void calculatePERatio(Stock stock, double price) throws Exception {
	     double result = stockMarketService.calculatePERatio(stock, price);
	     printResult("PE Ratio: " + result);
	  }

	  private static void calculateVolumeWeightedStockPrice(Stock stock) throws Exception {
	    List<Trade> trades = stockMarketService.getTrades(stock, 15);
	    if (trades == null || trades.isEmpty()) {
	      printResult("Volume Weighted Stock Price: No trades");
	    } else {
	      double result = stockMarketService.calculateVolumeWeightedStockPrice(trades);
	      printResult("Volume Weighted Stock Price: " + result);
	    }
	  }

	  private static void recordTrade(Stock stock, int quantity, TradeType type, double price) throws Exception {
		  stockMarketService.recordTrade(new Trade(stock, Calendar.getInstance().getTime(),
	        quantity, type, price));
	    printResult("Trade recorded");
	  }

	  private static void calculateGBCE() throws Exception {
	    List<Trade> allTrades = stockMarketService.getAllTrades();
	    if (allTrades == null || allTrades.isEmpty()) {
	      printResult("Unable to calculate GBCE: No trades");
	    } else {
	      printResult("GBCE: " + stockMarketService.calculateGBCEAllShareIndex(allTrades));
	    } 
	  }

	  private static void initStocks() {
		  stockMarketService.addStock(new Stock(stockMarketService.getStockSymbol().get(1l), StockType.COMMON, 0, 0, 100));
		  stockMarketService.addStock(new Stock(stockMarketService.getStockSymbol().get(2l), StockType.COMMON, 8, 0, 100));
		  stockMarketService.addStock(new Stock(stockMarketService.getStockSymbol().get(3l), StockType.COMMON, 23, 0, 60));
		  stockMarketService.addStock(new Stock(stockMarketService.getStockSymbol().get(4l), StockType.PREFERRED, 8, 2, 100));
		  stockMarketService.addStock(new Stock(stockMarketService.getStockSymbol().get(5l), StockType.PREFERRED, 13, 0, 250));
	  }
	  
	  private static void printResult(String result) {
	    System.out.println("-------------------------------------");
	    System.out.println(result);
	    System.out.println("-------------------------------------");
	  }

	public static Scanner getScanner() {
		return scanner;
	}

	public static void setScanner(Scanner scanner) {
		StockMarketApplication.scanner = scanner;
	}

}
