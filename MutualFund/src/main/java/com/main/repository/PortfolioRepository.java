package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.mutualfund.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer>{

}
