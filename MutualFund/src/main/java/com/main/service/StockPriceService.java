
package com.main.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.stock.StockPrice;
import com.main.repository.StockPriceRepository;

@Service
public class StockPriceService {
	
	@Autowired
	StockPriceRepository stockPriceRepository;
	
	public List<StockPrice> getAllStockPrice() {
		return stockPriceRepository.findAll();
	}
	
	
	public List<StockPrice> getStockPriceById(int stockId) {
		return stockPriceRepository.findByStockId(stockId);
	}

	
	public List<StockPrice> getStockPricesForCurrentDate() {
		return stockPriceRepository.findStockPricesWithCurrentDate();
	}
	
	
	public List<StockPrice> getStockPriceForCurrentDateById(int stockId) {
		return stockPriceRepository.findStockPricesWithCurrentDateAndStockId(stockId);
	}
	
	
	public StockPrice fetchStockPriceByIdAndDate(Date date, int stockId) {
		return stockPriceRepository.findByStockIdAndBusinessDate(stockId, date);
	}
	
}
