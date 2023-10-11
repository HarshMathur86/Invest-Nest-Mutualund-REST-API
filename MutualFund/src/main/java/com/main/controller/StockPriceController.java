
package com.main.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.stock.StockPrice;
import com.main.service.StockPriceService;

@RestController
public class StockPriceController {
	
	@Autowired
	StockPriceService stockPriceService;
	
	@GetMapping(value = "investnest/stockprices", produces="application/json")
	public ResponseEntity<?> getAllStockPrice() {
		try {
			List<StockPrice> stockPrices = stockPriceService.getAllStockPrice();
			if(stockPrices != null) {
				return new ResponseEntity<>(stockPrices, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occured while fetching data - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "investnest/stockprices/id/{id}", produces = "application/json")
	public ResponseEntity<?> getStockPriceById(@PathVariable("id") int stockId) {
		try {
			List<StockPrice> stockPricesById = stockPriceService.getStockPriceById(stockId);
			if(stockPricesById.size() == 0 || stockPricesById == null) {
				return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
			}
			else {
				return new ResponseEntity<>(stockPricesById, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occured while fetching data - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "investnest/stockprices/current-date", produces = "application/json")
	public List<StockPrice> getStockPriceCurrentDate() {
		return stockPriceService.getStockPricesForCurrentDate();
		
	}


	@GetMapping(value = "investnest/stockprice/current-date/id/{id}", produces = "application/json")
	public ResponseEntity<?> getStockPriceforCurrentDateById(@PathVariable("id") int stockId) {
		try {
			List<StockPrice> stockPrices = stockPriceService.getStockPriceForCurrentDateById(stockId);
			if(stockPrices.size() == 0 || stockPrices == null) {
				return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
			}
			else {
				return new ResponseEntity<>(stockPrices, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occured while fetching data - " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	 @GetMapping(value = "investnest/stockprice/id/{id}/fetch-by-date/{date}", produces="application/json")
	 public ResponseEntity<?> fetchStockPriceByIdAndDate(
	            @PathVariable("id") int stockId,
	            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

	        // Fetch stock price by both ID and date
	        StockPrice stockPrice = stockPriceService.fetchStockPriceByIdAndDate(date, stockId);

	        if (stockPrice != null) {
	            return ResponseEntity.ok(stockPrice);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}






