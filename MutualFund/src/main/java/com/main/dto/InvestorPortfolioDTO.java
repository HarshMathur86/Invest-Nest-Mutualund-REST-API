package com.main.dto;

public class InvestorPortfolioDTO {
	private String mfName;
	private double mfWorth;
	
	public InvestorPortfolioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvestorPortfolioDTO(String mfName, double mfWorth) {
		super();
		this.mfName = mfName;
		this.mfWorth = mfWorth;
	}

	public String getMfName() {
		return mfName;
	}

	public void setMfName(String mfName) {
		this.mfName = mfName;
	}

	public double getMfWorth() {
		return mfWorth;
	}

	public void setMfWorth(double mfWorth) {
		this.mfWorth = mfWorth;
	}
	
}
