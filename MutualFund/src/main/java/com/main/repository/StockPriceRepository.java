//package com.main.repository;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//
//import com.main.model.StockPrice;
//
//
//public interface StockPriceRepository extends CrudRepository<StockPrice, Integer>{
//	
//	@Query("SELECT MAX(sp.business_date) FROM StockPrice sp")
//	Date findMaxBusinessDate();
//	
//	ArrayList<StockPrice> findByBusinessDate(Date businessDate);
//
//}


package com.main.repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.model.stock.StockPrice;

public interface StockPriceRepository extends JpaRepository<StockPrice, Integer> {
	
	@Query("SELECT MAX(sp.businessDate) FROM StockPrice sp")
	Date findMaxBusinessDate();
	
	@Query("SELECT sp FROM StockPrice sp WHERE sp.businessDate = (SELECT MAX(sp2.businessDate) FROM StockPrice sp2)")
	ArrayList<StockPrice> findStockPricesWithMaxDate();
	
	@Query("SELECT sp FROM StockPrice sp WHERE TRUNC(sp.businessDate) = TRUNC(SYSDATE)")
	ArrayList<StockPrice> findStockPricesWithCurrentDate();
	
	@Query("SELECT sp FROM StockPrice sp WHERE TRUNC(sp.businessDate) = TRUNC(SYSDATE) AND sp.stockId = :stock_id")
	ArrayList<StockPrice> findStockPricesWithCurrentDateAndStockId(@Param("stock_id") int stock_id);
	
	@Query("SELECT sp.openingPrice FROM StockPrice sp WHERE TRUNC(sp.businessDate) = TRUNC(SYSDATE) AND sp.stockId = :stock_id")
	double findStockOpeningPriceOfCurrentDateAndStockId(@Param("stock_id") int stock_id);
	
//	@Query("SELECT sp.openingPrice FROM StockPrice sp WHERE sp.businessDate = (SELECT MAX(sp2.businessDate) FROM StockPrice sp2 WHERE sp2.stockId = :stock_id) AND sp.stockId = :stock_id")
//	double findStockOpeningPriceOfMaxDateAndStockId(@Param("stock_id") int stock_id);

	
	ArrayList<StockPrice> findByStockId(int stockId);
	
	ArrayList<StockPrice> findByBusinessDate(Date businessDate);
	
	StockPrice findByStockIdAndBusinessDate(int id, Date date);

}
