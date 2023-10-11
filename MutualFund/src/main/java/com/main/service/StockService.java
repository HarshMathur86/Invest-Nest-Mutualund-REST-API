package com.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.stock.Stock;
import com.main.repository.StockRepository;

@Service
public class StockService {
	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	StockPriceService stockPriceService;
	
	public List<Stock> getAllStock() {
		return stockRepository.findAll();
	}
	

	public List<Stock> getAllStocksForCurrentDate() {
		//Max date will return latest prices
		List<Stock> stocksWithLatestPrice = new ArrayList<Stock>();
		stocksWithLatestPrice = this.getAllStock();
		for(Stock s: stocksWithLatestPrice) {
			s.setStockPrices(stockPriceService.getStockPriceForCurrentDateById(s.getStockId()));
		}
		return stocksWithLatestPrice;
	}
	
	
	public Stock getStockForCurrentDate(int stockId) {
  		Stock stock = this.getStockById(stockId);
		stock.setStockPrices(stockPriceService.getStockPriceForCurrentDateById(stockId));
		return stock;
	}
	
	
	public Stock getStockById(int stockId) {
		return stockRepository.findByStockId(stockId);
	}
}
