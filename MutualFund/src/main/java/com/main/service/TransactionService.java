package com.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.InvestorPortfolioDTO;
import com.main.dto.InvestorTransactionDTO;
import com.main.exception.MutualFundPurchaseException;
import com.main.exception.MutualFundSellException;
import com.main.model.investor.InvestorPortfolio;
import com.main.model.investor.InvestorTransaction;
import com.main.model.mutualfund.MutualFund;
import com.main.repository.InvestorPortfolioRepository;
import com.main.repository.InvestorRepository;
import com.main.repository.InvestorTransactionRepository;
import com.main.repository.MutualFundRepository;

@Service
public class TransactionService {
	
	@Autowired
	InvestorPortfolioRepository investorPortfolioRepository;
	
	@Autowired
	MutualFundService mutualFundService;
	
	@Autowired
	MutualFundRepository mutualFundRepository;
	
	@Autowired
	InvestorTransactionRepository ivstTransacRepo;
	
	@Autowired
	InvestorRepository investorRepository;
	
	public boolean buyMutualFund(InvestorPortfolio ivstPortfolio) throws MutualFundPurchaseException {
		if(ivstPortfolio.getAmountInvested() == 0.0) {
			throw new MutualFundPurchaseException("Invalid purchase amount");
		}
		
		MutualFund mutualFund = mutualFundService.getMutualFundBy(ivstPortfolio.getMfId());
		
		// Checking if mutual fund id exist or not
		if(mutualFund == null) {
			//Mutual fund with given id doesn't exits
			throw new MutualFundPurchaseException("Given mutual fund ID doesn't exist");
		}
		
		// If investor already possess some units of same mutualfundid then getting it to append with new units
		double oldUnitsHeld, oldInvestedAmount;
		
		
		// updating this try catch to coding standard using conditional statements
		try {
			InvestorPortfolio existingPortfolio = investorPortfolioRepository.findByInvestorIdAndMfId(ivstPortfolio.getInvestorId(), ivstPortfolio.getMfId());
			System.out.println("Investor portfolio = " + existingPortfolio);
			oldUnitsHeld  = existingPortfolio.getUnits();
			oldInvestedAmount = existingPortfolio.getAmountInvested();
		
		} catch (Exception e) {
			// Investor Buying this mutual fund first time
			oldUnitsHeld = 0.0;
			oldInvestedAmount = 0.0;
		}
		
		
		double amountInvested = ivstPortfolio.getAmountInvested();
		double entryLoadFee = amountInvested * mutualFund.getEntryLoad() / 100 ;
		double netAmountInvested = ivstPortfolio.getAmountInvested() - entryLoadFee;
		double unitsBought = netAmountInvested / mutualFund.getNav();
		
		// Updating units Boughts in investor portfolio & saving in the database --> 
		// Possible exception investor id don't exist while 
		ivstPortfolio.setUnits(unitsBought + oldUnitsHeld);
		ivstPortfolio.setAmountInvested(netAmountInvested + oldInvestedAmount);
		try {
			ivstPortfolio = investorPortfolioRepository.save(ivstPortfolio);
		} catch (Exception e) {
			throw new MutualFundPurchaseException("Given investor ID doesn't exist");
		}
		
		//Updating the cash Balance & Total Corpus of mutual fund
		double updatedCashBalance = mutualFund.getCashBalance() + entryLoadFee;
		mutualFund.setCashBalance(updatedCashBalance);
		
		double updatedTotalCorpus = mutualFund.getTotalCorpus() + netAmountInvested;
		mutualFund.setTotalCorpus(updatedTotalCorpus);
		
		mutualFundRepository.save(mutualFund);
		
		// Adding the transaction details in the transaction table 
		InvestorTransaction ivstTransac = new InvestorTransaction();
		ivstTransac.setInvestorId(ivstPortfolio.getInvestorId());
		ivstTransac.setMfId(ivstPortfolio.getMfId());
		ivstTransac.setTransactionType("Buy");
		ivstTransac.setUnits(unitsBought);
		ivstTransac.setTransactionAmount(amountInvested);
		
		ivstTransacRepo.save(ivstTransac);
		
		return true;
	}
	
	public double sellMutualFund(int investorId, int mfId, double sellingAmount) throws MutualFundSellException {
		if(sellingAmount == 0.0) {
			throw new MutualFundSellException("Invalid selling Amount");
		}
		MutualFund mutualFund = mutualFundService.getMutualFundBy(mfId);
		
		// Checking if mutual fund id exist or not
		if(mutualFund == null) {
			//Mutual fund with given id doesn't exits
			throw new MutualFundSellException("Given mutual fund ID doesn't exist");
		}
		
		double oldUnitsHeld, currentMfWorth;
		InvestorPortfolio existingPortfolio;
		
		try {
			existingPortfolio = investorPortfolioRepository.findByInvestorIdAndMfId(investorId, mfId);
			oldUnitsHeld  = existingPortfolio.getUnits();
			// Price price of already held mutual fund
			currentMfWorth = existingPortfolio.getUnits() * mutualFund.getNav();
		} catch (Exception e) {
			// Investor don't own this particular mutual fund
			throw new MutualFundSellException("Investor don't own this mutual fund");
		}
		if(currentMfWorth < sellingAmount) {
			throw new MutualFundSellException("Selling more mutual funds than in possession");
		}
		
		double unitsSold = sellingAmount / mutualFund.getNav();
		double exitLoadFee = mutualFund.getExitLoad() * sellingAmount / 100 ;
		double netAmountReceivableToInvestor = sellingAmount - exitLoadFee;
		
		// Updating units in the investor portfolio
		// Check if updated units is zero if it is then delete the entry from the table
		existingPortfolio.setUnits(oldUnitsHeld - unitsSold);
		
		double newInvestmentAmount = existingPortfolio.getAmountInvested() - unitsSold * (existingPortfolio.getAmountInvested() / oldUnitsHeld);
		
		existingPortfolio.setAmountInvested(newInvestmentAmount);
		
		System.out.println("new units in portfolio = " + existingPortfolio.getUnits());
		System.out.println("new investment amount in portfolio = " + existingPortfolio.getAmountInvested());
		
		if(existingPortfolio.getUnits() != 0.0 && newInvestmentAmount > 0) {
			existingPortfolio = investorPortfolioRepository.save(existingPortfolio);
		}
		else {
			investorPortfolioRepository.delete(existingPortfolio);
		}
		
		
		// Updating mutual fund total corpus & cash balance
		double updatedCashBalance = mutualFund.getCashBalance() + exitLoadFee;
		mutualFund.setCashBalance(updatedCashBalance);
		
		double updatedTotalCorpus = mutualFund.getTotalCorpus() - sellingAmount;
		mutualFund.setTotalCorpus(updatedTotalCorpus);
		
		mutualFundRepository.save(mutualFund);
		
		// Adding the transaction details in the transaction table 
		InvestorTransaction ivstTransac = new InvestorTransaction();
		ivstTransac.setInvestorId(existingPortfolio.getInvestorId());
		ivstTransac.setMfId(existingPortfolio.getMfId());
		ivstTransac.setTransactionType("Sell");
		ivstTransac.setUnits(unitsSold);
		ivstTransac.setTransactionAmount(sellingAmount);
		
		ivstTransacRepo.save(ivstTransac);
		
		return netAmountReceivableToInvestor;
	}
	
	public List<InvestorTransactionDTO> getAllTransactions() {
		
		List<InvestorTransaction> investorTransactions = ivstTransacRepo.findAll();
		List<InvestorTransactionDTO> transactionResponse = new ArrayList<InvestorTransactionDTO>();
		
		for(InvestorTransaction ivstTransac : investorTransactions) {
			InvestorTransactionDTO transaction = new InvestorTransactionDTO();
			
			transaction.setMfName(mutualFundRepository.findMfNameByMfId(ivstTransac.getMfId()));
			transaction.setInvestorName(investorRepository.findFullNameByInvestorId(ivstTransac.getInvestorId()));
			transaction.setTransactionAmount(ivstTransac.getTransactionAmount());
			transaction.setTransactionDate(ivstTransac.getTransactionDate());
			transaction.setTransactionType(ivstTransac.getTransactionType());
			
			transactionResponse.add(transaction);
		}
		
		return transactionResponse;
	}
	
	public List<InvestorTransactionDTO> getTransactionsByInvestorId(int investorId) {
		
		List<InvestorTransaction> investorTransactions = ivstTransacRepo.findByInvestorId(investorId);
		List<InvestorTransactionDTO> transactionResponse = new ArrayList<InvestorTransactionDTO>();
		String investorName = investorRepository.findFullNameByInvestorId(investorId);
		
		for(InvestorTransaction ivstTransac : investorTransactions) {
			InvestorTransactionDTO transaction = new InvestorTransactionDTO();
			
			transaction.setMfName(mutualFundRepository.findMfNameByMfId(ivstTransac.getMfId()));
			transaction.setInvestorName(investorName);
			transaction.setTransactionAmount(ivstTransac.getTransactionAmount());
			transaction.setTransactionDate(ivstTransac.getTransactionDate());
			transaction.setTransactionType(ivstTransac.getTransactionType());
			
			transactionResponse.add(transaction);
		}
		
		return transactionResponse;
	}
	
	public List<InvestorPortfolioDTO> getInvestorPortfolio(int investorId) {
		List<InvestorPortfolio> ivstPortfolio = investorPortfolioRepository.findByInvestorId(investorId);
		List<InvestorPortfolioDTO> resultPortfolio = new ArrayList<InvestorPortfolioDTO>();
		
		for(InvestorPortfolio ip : ivstPortfolio) {
			InvestorPortfolioDTO portfolio = new InvestorPortfolioDTO();
			portfolio.setMfName(mutualFundRepository.findMfNameByMfId(ip.getMfId()));
			
			MutualFund mf = mutualFundService.getMutualFundBy(ip.getMfId());
			portfolio.setMfWorth(mf.getNav() * ip.getUnits());
			
			resultPortfolio.add(portfolio);
		}
		return resultPortfolio;
	}
}
