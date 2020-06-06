package com.acme.mytrader.strategy;

import com.acme.mytrader.price.*;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {
	public static void main(String[] args)
	{
		PriceListenerImp priceListener = new PriceListenerImp("IBM");
		double targetPrice = 55;
		int purchaseVolume = 100;
		String security = "AuthenticatedUser";
		int monitorIntervalSeconds = 1;
		priceListener.monitorAndBuy(targetPrice, purchaseVolume, monitorIntervalSeconds, security);
		//System.out.println("Stocks Purchased " + priceListener.getPurchasedStocks() + " for value " + priceListener.getLastPurchasedStockPrice());
	}
}
