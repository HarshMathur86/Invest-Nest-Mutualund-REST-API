package com.main.json;

public class PortfolioJson {
	private int stockId;
	private double weightage;
	private double stockUnits;
	
	
	//Default Constructor
	public PortfolioJson() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PortfolioJson(int stockId, double weightage, double stockUnits) {
		super();
		this.stockId = stockId;
		this.weightage = weightage;
		this.stockUnits = stockUnits;
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

	
	
}
