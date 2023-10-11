package com.main.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.portfoliomanager.PortfolioManager;
import com.main.repository.PortfolioManagerRepository;

@RestController
public class PortfolioManagerController {
	
	@Autowired
	PortfolioManagerRepository portfolioManagerRepository;
	
	@PostMapping(value = "investnest/portfolio-manager/validate", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> validateInvestor(@RequestBody Map<String, String> loginCredentials) {
		try {
			PortfolioManager pm = portfolioManagerRepository.findByPmId(1);
		
			if(loginCredentials.get("username").equals(pm.getUsername()) && loginCredentials.get("password").equals(pm.getPassword())) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Incorrect Credentials", HttpStatus.NOT_FOUND);
			}
		} catch (NullPointerException nullPointerExp) {
			return new ResponseEntity<>("Invalid json request", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occured while validating user - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
