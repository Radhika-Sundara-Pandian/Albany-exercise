package com.acme.mytrader.execution;

public class ExecutionServiceImp implements ExecutionService {

	private double purchasedStockPrice;
	private int purchasedStockVolume;
	private final String SECURITY_STRING = "AuthenticatedUser";
	
	@Override
	public void buy(String security, double price, int volume) {
		
		if(SECURITY_STRING.equals(security)) {
			this.purchasedStockPrice = price;
			this.purchasedStockVolume = volume;
			System.out.println("Stocks Purchased " + volume + " at price " + price);
			// TODO Buy transaction for stocks
		}
		else {
			System.out.println("Invalid User");
		}
		
	}

	@Override
	public void sell(String security, double price, int volume) {
		// TODO Sell transaction for stocks
		
	}

	public double getPurchasedStockPrice() {
		return purchasedStockPrice;
	}

	public int getPurchasedStockVolume() {
		return purchasedStockVolume;
	}

}
