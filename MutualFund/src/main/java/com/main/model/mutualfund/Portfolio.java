package com.main.model.mutualfund;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.model.stock.Stock;


@Entity
@Table(name = "Portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "portfolio_id")
    @SequenceGenerator(sequenceName = "portfolio_id_seq", allocationSize = 1, name = "portfolio_id")
    private int portfolioId;

    @Column 
    private int mfId;
    
    @Column 
    private int stockId;
    
    @Column(name = "weightage")
    private double weightage;
    
    @Column
    private double stockUnits;
    
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mfId", referencedColumnName = "mfId", insertable = false, updatable = false)
    private MutualFund mutualFund;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stockId", referencedColumnName = "stockId", insertable = false, updatable = false)
    private Stock stock;

	public Portfolio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Portfolio(int portfolioId, int mfId, int stockId, double weightage, double stockUnits, MutualFund mutualFund,
			Stock stock) {
		super();
		this.portfolioId = portfolioId;
		this.mfId = mfId;
		this.stockId = stockId;
		this.weightage = weightage;
		this.stockUnits = stockUnits;
		this.mutualFund = mutualFund;
		this.stock = stock;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public int getMfId() {
		return mfId;
	}

	public void setMfId(int mfId) {
		this.mfId = mfId;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public double getWeightage() {
		return weightage;
	}

	public void setWeightage(double weightage) {
		this.weightage = weightage;
	}

	public double getStockUnits() {
		return stockUnits;
	}

	public void setStockUnits(double stockUnits) {
		this.stockUnits = stockUnits;
	}

	public MutualFund getMutualFund() {
		return mutualFund;
	}

	public void setMutualFund(MutualFund mutualFund) {
		this.mutualFund = mutualFund;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

    
	
}
