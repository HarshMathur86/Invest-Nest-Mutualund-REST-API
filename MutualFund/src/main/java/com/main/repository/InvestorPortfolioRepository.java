package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.investor.InvestorPortfolio;

public interface InvestorPortfolioRepository extends JpaRepository<InvestorPortfolio, Integer>{
	// Declare a query method to find by investorId and mfId
    InvestorPortfolio findByInvestorIdAndMfId(int investorId, int mfId);
    
    List<InvestorPortfolio> findByInvestorId(int investorId);
}
