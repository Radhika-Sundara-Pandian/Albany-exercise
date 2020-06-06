package com.acme.mytrader.strategy;

import org.junit.Test;
import static org.junit.Assert.*;
import com.acme.mytrader.price.PriceListenerImp;

public class TradingStrategyTest {
    
	@Test
    public void testTradingStocksPurchased() {
    	
    	PriceListenerImp priceListener = new PriceListenerImp("IBM");
    	priceListener.monitorAndBuy(21, 100, 1, "AuthenticatedUser");
    	assertEquals("Stocks Purchased", 100, priceListener.getPurchasedStocks());
    	
    }
	
	@Test
    public void testStocksPurchasedLessThanTarget() {
    	
    	PriceListenerImp priceListener = new PriceListenerImp("IBM");
    	priceListener.monitorAndBuy(55, 100, 1, "AuthenticatedUser");
    	assertTrue("Purchased Below Target Price", priceListener.getLastPurchasedStockPrice() < 55);
    	
    }
    
    @Test
    public void testTradingStocksFailureDueToSecurity() {
    	PriceListenerImp priceListener = new PriceListenerImp("IBM");
    	priceListener.monitorAndBuy(21, 100, 1, "Test");
    	assertNotEquals("Purchase cancelled due to security mismatch", 100, priceListener.getPurchasedStocks());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionThrownForPurchaseVolume() {
    	PriceListenerImp priceListener = new PriceListenerImp("IBM");
    	priceListener.monitorAndBuy(21, 0, 1, "Test");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionThrownForNegativeInterval() {
    	PriceListenerImp priceListener = new PriceListenerImp("IBM");
    	priceListener.monitorAndBuy(21, 200, -1, "Test");
    }
    
    @Test
    public void testMultipleStocksPurchased() {
    	
    	PriceListenerImp priceListener = new PriceListenerImp("IBM");
    	priceListener.monitorAndBuy(21, 100, 1, "AuthenticatedUser");
    	priceListener.monitorAndBuy(15, 50, 1, "AuthenticatedUser");
    	assertEquals("Stocks Purchased", 150, priceListener.getPurchasedStocks());
    	
    }
}
