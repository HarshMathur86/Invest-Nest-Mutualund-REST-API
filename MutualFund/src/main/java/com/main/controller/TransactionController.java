package com.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.InvestorTransactionDTO;
import com.main.exception.MutualFundPurchaseException;
import com.main.exception.MutualFundSellException;
import com.main.model.investor.InvestorPortfolio;
import com.main.service.TransactionService;

@RestController
public class TransactionController {
	@Autowired
	TransactionService transactionService;
	
	// Post request of buying new Mutual Fund
    @PostMapping(value = "investnest/transaction/buy-mutualfund", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> buyMutualFund(@RequestBody InvestorPortfolio invstPortfolio) {
		System.out.println("buy mutual fund - POST - controller");
		boolean status = false;
		
		try {
			
			status = transactionService.buyMutualFund(invstPortfolio);
			if(status == true) {
				return new ResponseEntity<>("MutualFund bought successfully", HttpStatus.OK);
			}
			else {
				throw new MutualFundPurchaseException("Unable to purchase mutual fund");
			}
		
		} 
		catch (MutualFundPurchaseException mfPurchaseExp) {
			return new ResponseEntity<>(mfPurchaseExp.getMessage(), HttpStatus.BAD_REQUEST);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Exception occure while buying mutual fund - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    // Post request of selling Mutual Fund
    @PostMapping(value = "investnest/transaction/sell-mutualfund", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> sellMutualFund(@RequestBody Map<String, Double> sellingMfDetails) {
		System.out.println("buy mutual fund - POST - controller");
		int investorId, mfId;
		double sellingAmount;
		
		try {
		
			investorId = (int) Math.round(sellingMfDetails.get("investorId"));
			mfId = (int)Math.round(sellingMfDetails.get("mfId"));
			sellingAmount = sellingMfDetails.get("amount");
			double amountReceived = transactionService.sellMutualFund(investorId, mfId, sellingAmount);
			return new ResponseEntity<>("Mutual fund sold successfully Amount - " + amountReceived + " is transfered into your bank account", HttpStatus.OK);
		
		} 
		catch (MutualFundSellException mfSellExp) {
			return new ResponseEntity<>(mfSellExp.getMessage(), HttpStatus.BAD_REQUEST);
		} 
		catch (NullPointerException nullPointerExp) {
			// When all the fields of the id is not provided
			return new ResponseEntity<>("Invalid json request", HttpStatus.BAD_REQUEST);
		} 
		catch (Exception e) {
			return new ResponseEntity<>("Exception occured while selling mutual fund - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    //get - all transaction for portfolio manager
    @GetMapping(value = "investnest/transactions", produces="application/json")
	public ResponseEntity<?> getAllTransactions() {
		try {
			List<InvestorTransactionDTO> transactions = transactionService.getAllTransactions();
			if(transactions != null) {
				return new ResponseEntity<>(transactions, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Exception occured while fetching data - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
    
}
