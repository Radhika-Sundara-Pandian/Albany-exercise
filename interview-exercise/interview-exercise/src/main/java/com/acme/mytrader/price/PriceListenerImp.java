package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionServiceImp;

public class PriceListenerImp implements PriceListener {

	// Stock which is monitored e.g "IBM
	private String stockName;
	
	// Target price at which buy need to be executed
	private double targetPrice;
	
	// Volume of stocks to be purchased in single buy
	private int purchaseVolume;
	
	// Total volume of stocks purchased by multiple buy options
	private int totalStocksPurchased = 0;
	
	// Price at which stocks were purchased
	private double lastPurchasedStockPrice = 0;
	
	public PriceListenerImp(String stockName) {
		this.stockName = stockName;
	}
	
	/**
	 * Notified by the Observer about price update
	 */
	@Override
	public void priceUpdate(String security, double price) {
		if(price < targetPrice) {
			ExecutionServiceImp executionService = new ExecutionServiceImp();
			executionService.buy(security, price, purchaseVolume);
			PriceSourceImp.getInstance().removePriceListener(this);
			int purchasedVolume = executionService.getPurchasedStockVolume();
			
			//Would be 0 if the buy transaction is not successful
			if(purchasedVolume > 0) {
				totalStocksPurchased += purchasedVolume;
				this.lastPurchasedStockPrice = price;
			}
		}
	}
	
	/**
	 * Monitor stock price for the listener at specified intervals. 
	 * Registers with the source for updates in stock price
	 * @param targetPrice value below which stocks need to be purchased
	 * @param purchaseVolume volume of stocks 
	 * @param monitorInterval interval at which stocks need to be monitored in seconds
	 * @param security security code for purchase
	 */
	public void monitorAndBuy(double targetPrice, int purchaseVolume, int monitorInterval, String security) {
		if(purchaseVolume < 1)
		{
			throw new IllegalArgumentException("Purchase volume should be positive integer");
		}
		if(monitorInterval < 1)
		{
			throw new IllegalArgumentException("Monitor Interval should be positive integer");
		}
		this.targetPrice = targetPrice;
		this.purchaseVolume = purchaseVolume;
		PriceSourceImp.getInstance().addPriceListener(this);
		PriceSourceImp.getInstance().monitorStockPriceUpdate(this.stockName, security, monitorInterval);
	}

	public int getPurchasedStocks() {
		return totalStocksPurchased;
	}

	public double getLastPurchasedStockPrice() {
		return lastPurchasedStockPrice;
	}
}
