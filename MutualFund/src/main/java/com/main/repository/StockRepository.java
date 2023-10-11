package com.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.stock.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer>{
	
	Stock findByStockId(int stockId);
}
