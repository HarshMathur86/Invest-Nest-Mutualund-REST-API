package com.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.model.investor.Investor;

public interface InvestorRepository extends JpaRepository<Investor, Integer> {
	Investor findByInvestorId(int investorId);
	
    List<Investor> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String lastName);
	
	Optional<Investor> findByEmail(String email);

	List<Investor> findByUsername(String username);
	
	@Query("SELECT CONCAT(i.firstName, ' ', i.lastName) FROM Investor i WHERE i.investorId = :investorId")
    String findFullNameByInvestorId(@Param("investorId") int investorId);
	
}