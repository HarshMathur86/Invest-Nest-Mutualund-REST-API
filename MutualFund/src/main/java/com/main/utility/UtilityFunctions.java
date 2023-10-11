package com.main.utility;

import java.util.List;
import java.util.Map;

import com.main.json.MutualFundJson;
import com.main.json.PortfolioJson;
import com.main.model.mutualfund.MutualFund;
import com.main.model.mutualfund.Portfolio;

public class UtilityFunctions {
	
	
	public static void calculateNAV(MutualFund mutualFund, MutualFundJson mfJson, Map<Integer, Double> allocatedStockPrices) {
		/**
		 * Calculate and update the nav in the mutualfund object. 
		 * Also, updates the number of individual stock units present in the mutual fund in the MutualFundJson Object
		 */
		
		double units = 0;
		List<PortfolioJson> stockWeightages = mfJson.getPorfolio();
		
		for(PortfolioJson pj : stockWeightages) {
			int stockId = pj.getStockId();
			
			double individualStockunits = mutualFund.getTotalCorpus() * pj.getWeightage() / (100 * allocatedStockPrices.get(stockId));
			units += individualStockunits;
			
			// Storing the units of each stocks 
			stockWeightages.get(stockWeightages.indexOf(pj)).setStockUnits(individualStockunits);
		}
		
		mutualFund.setUnits(units);
		
		// Calculating NAV
		double nav = (mutualFund.getTotalCorpus() - mutualFund.getTotalCorpus() * mutualFund.getExpenseRatio() / 100 ) / units;
		mutualFund.setNav(nav);
	}
	
	public static double calculateCurrentNAV(List<Portfolio> mfStocks, Map<Integer, Double> currentStockPrices) {
		double newInvestmentAmount = 0;
		double totalStockUnits = 0;
		for(Portfolio pf : mfStocks) {
			newInvestmentAmount += currentStockPrices.get(pf.getStockId()) * pf.getStockUnits();
			totalStockUnits += pf.getStockUnits();
		}
		double newNav = newInvestmentAmount / totalStockUnits;
		return Math.round(newNav * 100.0) / 100.0;
	}
	
	
}
