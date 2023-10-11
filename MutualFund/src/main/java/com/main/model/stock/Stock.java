package com.main.model.stock;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Stock")
public class Stock {
	@Id
	@Column
	int stockId;
	
	@Column
	String stockName;
	
	
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "stockId", referencedColumnName = "stockId", insertable = false, updatable = false)
    private List<StockPrice> stockPrices;

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stock(int stockId, String stockName, List<StockPrice> stockPrices) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.stockPrices = stockPrices;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public List<StockPrice> getStockPrices() {
		return stockPrices;
	}

	public void setStockPrices(List<StockPrice> stockPrices) {
		this.stockPrices = stockPrices;
	}
	
}
