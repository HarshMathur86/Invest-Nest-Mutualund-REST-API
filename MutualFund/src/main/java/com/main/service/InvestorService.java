package com.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.exception.InvalidJsonRequestException;
import com.main.model.investor.Investor;
import com.main.repository.InvestorRepository;

@Service
public class InvestorService {
	
	@Autowired
	InvestorRepository investorRepository;
	
	public List<Investor> getAllInvestors() {
		return investorRepository.findAll();
	}
	
	// Returning details of investor
	public Map<String, String> getInvestorById(int investorId) {
		Investor investor = investorRepository.findByInvestorId(investorId);
		Map<String, String> investorDetails = new HashMap<>();
		
		if (investor != null) {
			investorDetails.put("investorId", String.valueOf(investor.getInvestorId()));
		    investorDetails.put("firstName", investor.getFirstName());
		    investorDetails.put("lastName", investor.getLastName());
		} 
		else {
			investorDetails.put("message", "Investor not found");
		}
		return investorDetails;
	}
	
	public Investor addInvestor(Investor investor) {
		return investorRepository.save(investor);
	}
	
	public List<Investor> getInvestorsByName(String name) {
        return investorRepository.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(name, name);
    }
    
    public boolean getInvestorByEmail(String email) {
        if(investorRepository.findByEmail(email).isPresent())
        	return true;
        else
        	return false;
    }
    
    
    public Map<String, String> validateInvestor(Map<String, String> loginCredentials) throws InvalidJsonRequestException {
    	if(!loginCredentials.containsKey("username") || !loginCredentials.containsKey("password")) {
    		throw new InvalidJsonRequestException("Json doesn't contain password or username");
    	}
    	List<Investor> investors = investorRepository.findByUsername(loginCredentials.get("username"));
    	if(investors == null) {
    		return null;
    	}
    	for(Investor i : investors) {
    		if(i.getUsername().equals(loginCredentials.get("username")) && i.getPassword().equals(loginCredentials.get("password"))) {
    			return this.getInvestorById(i.getInvestorId());
    		}
    	}
    	return null;
    }
    

	public boolean updateInvestor(int investorId, Investor updatedInvestor) {
    	
    	Investor existingInvestor;
		try {
			existingInvestor = investorRepository.findByInvestorId(investorId);
			if(existingInvestor == null) {
				// Investor doesn't exists
				return false;
			}
		} catch (Exception e) {
			return false;
		}
        
        if (updatedInvestor.getUsername() != null) {
        	existingInvestor.setUsername(updatedInvestor.getUsername());
        }
        if (updatedInvestor.getPassword() != null) {
        	existingInvestor.setPassword(updatedInvestor.getPassword());
        }
        if (updatedInvestor.getFirstName() != null) {
        	existingInvestor.setFirstName(updatedInvestor.getFirstName());
        }
        if (updatedInvestor.getLastName() != null) {
        	existingInvestor.setLastName(updatedInvestor.getLastName());
        }
        if (updatedInvestor.getEmail() != null) {
        	existingInvestor.setEmail(updatedInvestor.getEmail());
        }
        if (updatedInvestor.getPhoneNumber() != 0) {
            existingInvestor.setPhoneNumber(updatedInvestor.getPhoneNumber());
        }
        
        investorRepository.save(existingInvestor);
        return true;
    }
}
