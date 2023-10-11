package com.main.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.InvestorPortfolioDTO;
import com.main.dto.InvestorTransactionDTO;
import com.main.exception.InvalidJsonRequestException;
import com.main.model.investor.Investor;
import com.main.service.InvestorService;
import com.main.service.TransactionService;

@RestController
public class InvestorController {
	
	@Autowired
	InvestorService investorService;
	
	@Autowired
	TransactionService transactionService;
	
	
	// Checks whether the api request is from authentic source or not
	@GetMapping(value = "investnest/investor/id/{id}", produces = "application/json")
    public ResponseEntity<?> getInvestorById(@PathVariable("id") int investorId,
                                               @RequestHeader("Authorization") String authHeader) {
		// Decoding the authHeader and extracting username and password
        try {
			String credentials = authHeader.substring("Basic".length()).trim();
			byte[] decoded = Base64.getDecoder().decode(credentials);
			String decodedCredentials = new String(decoded, StandardCharsets.UTF_8);
			//String encodedPassword = credentials.split(":", 2)[0]; // NR
			String[] parts = decodedCredentials.split(":", 2);
			String username = parts[0];
			String password = parts[1];
			//System.out.println(password + " --> " + encodedPassword); //NR
			
			Map<String, String> loginCredentials = new HashMap<>();
			loginCredentials.put("username", username);
			loginCredentials.put("password", password);
			
			Map<String, String> investorDetails = investorService.validateInvestor(loginCredentials);
			
  			if(investorDetails != null && investorDetails.get("investorId").equals(investorId + "")) {
				return new ResponseEntity<>(investorService.getInvestorById(investorId), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Incorrect Credentials", HttpStatus.UNAUTHORIZED);
			}
		} catch (InvalidJsonRequestException ije) {
			return new ResponseEntity<>("Unable to authenticate user", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Exception while fetching investor details - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
	
	@PostMapping(value = "investnest/investor/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addInvestor(@RequestBody Investor investor) {
		try {
			Investor newInvestor = investorService.addInvestor(investor);
			if(newInvestor != null)
				return new ResponseEntity<>("Investor registered successfully", HttpStatus.CREATED);
			else
				return new ResponseEntity<>("There is a problem while registering investor", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception while registering investor - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
    
	// '.+' --> allows character after the .
    @GetMapping(value = "investnest/investor/validate/email/{email:.+}", produces = "application/json")
    public ResponseEntity<?> getInvestorByEmail(@PathVariable("email") String email) {
        try {
			if(investorService.getInvestorByEmail(email)) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Exception while checking email occured - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @PatchMapping(value = "investnest/investor/id/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateInvestor(@PathVariable("id") int investorId, @RequestBody Investor updatedInvestor) {
        try {
			if(investorService.updateInvestor(investorId, updatedInvestor)) {
				return new ResponseEntity<>(true, HttpStatus.CREATED);
			}
			else {
				// Investor id given doesn't exists in the database
				return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Exception while updating investor details occured - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @PostMapping(value = "investnest/investor/validate", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> validateInvestor(@RequestBody Map<String, String> loginCredentials) {
		try {
			Map<String, String> investorDetails = investorService.validateInvestor(loginCredentials);
			
			if(investorDetails != null) {
				return new ResponseEntity<>(investorDetails, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Incorrect Credentials", HttpStatus.NOT_FOUND);
			}
		} catch (InvalidJsonRequestException ijr) {
			return new ResponseEntity<>(ijr.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occured while validating user - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
    
  //Get - investor transactions by id for investor
    @GetMapping(value = "investnest/investor/transaction/id/{id}", produces="application/json")
	public ResponseEntity<?> getInvestorTransactions(@PathVariable("id") int investorId) {
		try {
			List<InvestorTransactionDTO> transactions = transactionService.getTransactionsByInvestorId(investorId);
			
			if(transactions.size() != 0) {
				return new ResponseEntity<>(transactions, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Transactions not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Exception occured while fetching data - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
    
    // Get - Investor portfolio
    @GetMapping(value = "investnest/investor/portfolio/id/{id}", produces="application/json")
	public ResponseEntity<?> getAllTransactions(@PathVariable("id") int investorId) {
		try {
			List<InvestorPortfolioDTO> portfolio = transactionService.getInvestorPortfolio(investorId);
			if(portfolio.size() != 0) {
				return new ResponseEntity<>(portfolio, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Portfolio not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Exception occured while fetching data - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
    
    
    // Not required
 	@GetMapping(value = "investnest/investors", produces = "application/json")
 	public List<Investor> getAllInvestors() {
 		System.out.println("All Investors - GET - controller");
 		return investorService.getAllInvestors();
 	}
 	
 	// Not required
 	@GetMapping(value = "investnest/investor/name/{name}", produces = "application/json")
 	public List<Investor> getInvestorsByName(@PathVariable("name") String name) {
        System.out.println("Investors by name - GET - controller");
        return investorService.getInvestorsByName(name);
    } 
}
