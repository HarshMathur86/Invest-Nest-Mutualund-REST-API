package com.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.model.mutualfund.MutualFund;

public interface MutualFundRepository extends JpaRepository<MutualFund, Integer> {
	MutualFund findBymfId(int mfId);
	
	@Query("SELECT mf.mfName FROM MutualFund mf WHERE mf.mfId = :mfId")
    String findMfNameByMfId(@Param("mfId") int mfId);
}
