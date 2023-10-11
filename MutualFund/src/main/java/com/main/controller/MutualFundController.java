package com.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.json.MutualFundJson;
import com.main.model.mutualfund.MutualFund;
import com.main.service.MutualFundService;

@RestController
public class MutualFundController {
	@Autowired
	MutualFundService mutualFundService;
	

    @GetMapping(value = "/investnest/mutualfunds", produces = "application/json")
    public ResponseEntity<?> getAllMutualFunds() {
    	// Exception handling is done in the service function
    	return mutualFundService.getAllMutualFund();
    }
	

    @GetMapping(value = "/investnest/mutualfund/id/{id}", produces = "application/json")
    public ResponseEntity<?> getAllMutualFunds(@PathVariable("id") int mfId) {
        try {
			MutualFund mutualFund = mutualFundService.getMutualFundBy(mfId);
			if(mutualFund == null) {
				return new ResponseEntity<>("Mutual Fund of id " + mfId + " not found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(mutualFund, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Exception while fetching mutual fund detials - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
	// Post request of creating new Mutual Fund
    @PostMapping(value = "investnest/mutualfund/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> createMutualFund(@RequestBody MutualFundJson mfJson) {
		// Exception handling is done in the service function
    	return mutualFundService.createMutualFund(mfJson);
	}
    
}
