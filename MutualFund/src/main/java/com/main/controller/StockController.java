package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.stock.Stock;
import com.main.service.StockService;

@RestController
public class StockController {
	@Autowired
	StockService stockService;
	
	@GetMapping(value = "investnest/stocks", produces="application/json")
	public ResponseEntity<?> getAllStock() {
		try {
			List<Stock> stocks = stockService.getAllStock();
			if(stocks != null) {
				return new ResponseEntity<>(stocks, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occured while fetching data - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "investnest/stocks/current-date", produces = "application/json")
	public ResponseEntity<?> getStocksLatest() {
		try {
			return new ResponseEntity<>(stockService.getAllStocksForCurrentDate(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occured while fetching data - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value = "investnest/stock/current-date/id/{id}", produces = "application/json")
	public ResponseEntity<?> getStockLatestById(@PathVariable("id") int stockId) {
		try {
			Stock stock = stockService.getStockForCurrentDate(stockId);
			if(stock != null) {
				return new ResponseEntity<>(stock, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
			}
		} catch (NullPointerException npe){
			return new ResponseEntity<>("Given stock ID doesn't exits", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occured while fetching data - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping(value = "investnest/stock/id/{id}", produces = "application/json")
	public ResponseEntity<?> getStockById(@PathVariable("id") int stockId) {
		try {
			Stock stock = stockService.getStockById(stockId);
			if(stock != null) {
				return new ResponseEntity<>(stock, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occured while fetching data - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
