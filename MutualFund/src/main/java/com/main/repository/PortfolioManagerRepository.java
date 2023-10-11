package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.portfoliomanager.PortfolioManager;

public interface PortfolioManagerRepository extends JpaRepository<PortfolioManager, Integer>{
	PortfolioManager findByPmId(int pmId);
}
