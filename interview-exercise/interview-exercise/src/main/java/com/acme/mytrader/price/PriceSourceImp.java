package com.acme.mytrader.price;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PriceSourceImp implements PriceSource {

	private static PriceSourceImp instance;
	
	private PriceSourceImp() {}
	
	private List<PriceListener> listenerList = new ArrayList<PriceListener>(); 

	public static synchronized PriceSourceImp getInstance() {
		if(instance == null) {
			instance = new PriceSourceImp();
		}
		return instance;	
	}
	
	@Override
	public void addPriceListener(PriceListener listener) {
		listenerList.add(listener);
		
	}

	@Override
	public void removePriceListener(PriceListener listener) {
		listenerList.remove(listener);
	}
	
	/**
	 * Monitors the stock price updates at specified intervals and notifies the listeners about the update
	 * @param stockName stock that is monitored
	 * @param security security code 
	 * @param monitorInterval interval for monitoring
	 */
	public void monitorStockPriceUpdate(String stockName, String security, int monitorInterval) {
		double previousPrice = 0;
		while(!listenerList.isEmpty()) {
			double latestPrice = getStockPrice(stockName);
			if(latestPrice != previousPrice) {
				for(int i = 0; i < listenerList.size(); i ++) {
					PriceListener listener = listenerList.get(i);
					listener.priceUpdate(security, latestPrice);
				}
				previousPrice = latestPrice;
				try {
					Thread.sleep(monitorInterval * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
    private double getStockPrice(String stockName) {
    	Random stockPriceRandom = new Random();
    	double stockPrice = stockPriceRandom.nextDouble() * 100;
    	System.out.println("Monitoring for stock " + stockName + " price " + stockPrice);
    	return stockPrice;
    }
}
