package com.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.main.json.MutualFundJson;
import com.main.json.PortfolioJson;
import com.main.model.mutualfund.MutualFund;
import com.main.model.mutualfund.Portfolio;
import com.main.repository.MutualFundRepository;
import com.main.repository.PortfolioRepository;
import com.main.repository.StockPriceRepository;
import com.main.utility.UtilityFunctions;

@Service
public class MutualFundService {
	@Autowired
	MutualFundRepository mutualFundRepository;
	
	@Autowired
	PortfolioRepository portfolioRepository;
	
	@Autowired
	StockPriceService stockPriceService;
	
	@Autowired
	StockPriceRepository stockPriceRepository;
	
	@Transactional
	public ResponseEntity<String> createMutualFund(MutualFundJson mfJson) {

		try {
			double NFO_AMOUNT = 1000000000.00;
			
			MutualFund mutualFund = new MutualFund();
			
			// First storing the details received from the frontend via API post request to MF Object
			mutualFund.setMfName(mfJson.getMfName());
			mutualFund.setCashBalance(mfJson.getCashBalance());
			mutualFund.setEntryLoad(mfJson.getEntryLoad());
			mutualFund.setExitLoad(mfJson.getExitLoad());
			mutualFund.setExpenseRatio(mfJson.getExpenseRatio());
			mutualFund.setTotalCorpus(NFO_AMOUNT - mfJson.getCashBalance());

			// Extracting stock price from database
			Map<Integer, Double> stockOpeningPrices = new HashMap<Integer, Double>();
			for(PortfolioJson pj : mfJson.getPorfolio()) {
				stockOpeningPrices.put(pj.getStockId(), stockPriceRepository.findStockOpeningPriceOfCurrentDateAndStockId(pj.getStockId()));
			}
			
			UtilityFunctions.calculateNAV(mutualFund, mfJson, stockOpeningPrices);
			
			// Saving Mutual fund in database
			mutualFund = mutualFundRepository.save(mutualFund);
			
			for(PortfolioJson pj : mfJson.getPorfolio()) {
				Portfolio individualStock = new Portfolio();
				individualStock.setStockId(pj.getStockId());
				individualStock.setWeightage(pj.getWeightage());;
				individualStock.setMfId(mutualFund.getMfId());
				individualStock.setStockUnits(pj.getStockUnits());
				
				// Saving the stock into database
				portfolioRepository.save(individualStock);
			}
			return new ResponseEntity<>("Mutual Fund Created Successfully", HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Error while creating mutual fund - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	public void calculateCurrentNavByMutualFund(MutualFund mutualFund) {
		
		Map<Integer, Double> currentStockPrices = new HashMap<Integer, Double>();
		List<Portfolio> mfPortofolios = mutualFund.getPortfolios();
		for(Portfolio pf : mfPortofolios) {
			currentStockPrices.put(pf.getStockId(), stockPriceRepository.findStockOpeningPriceOfCurrentDateAndStockId(pf.getStockId()));
		}
		
		double newNav = UtilityFunctions.calculateCurrentNAV(mfPortofolios, currentStockPrices);
		mutualFund.setNav(newNav);
	
	}
	
	public ResponseEntity<?> getAllMutualFund() {
		try {
			List<MutualFund> mutualFunds = mutualFundRepository.findAll();
			
			for(MutualFund mf : mutualFunds) {
				this.calculateCurrentNavByMutualFund(mf);
			}
			return new ResponseEntity<>(mutualFunds, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception while fetching mutual fund details - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	public MutualFund getMutualFundBy(int mfId){
		MutualFund mutualFund = mutualFundRepository.findBymfId(mfId);
		if(mutualFund == null)
			return null;
		this.calculateCurrentNavByMutualFund(mutualFund);
		return mutualFund;
	}
	
}
