package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.investor.InvestorTransaction;

public interface InvestorTransactionRepository extends JpaRepository<InvestorTransaction, Integer>{
	List<InvestorTransaction> findByInvestorId(int investorId);
}
