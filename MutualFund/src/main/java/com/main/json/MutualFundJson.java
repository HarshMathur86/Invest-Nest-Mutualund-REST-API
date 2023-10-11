package com.main.json;

import java.util.List;

public class MutualFundJson {
	private String mfName;
	private double cashBalance;
	private double entryLoad;
	private double exitLoad;
	private double expenseRatio;
	private List<PortfolioJson> porfolio;
	
	
	public MutualFundJson() {
		super();
		// TODO Auto-generated constructor stub
	}


	//Constructor using fields
	public MutualFundJson(String mfName, double cashBalance, double entryLoad, double exitLoad, double expenseRatio,
			List<PortfolioJson> porfolio) {
		super();
		this.mfName = mfName;
		this.cashBalance = cashBalance;
		this.entryLoad = entryLoad;
		this.exitLoad = exitLoad;
		this.expenseRatio = expenseRatio;
		this.porfolio = porfolio;
	}
	
	
	//Getters and Setters
	public String getMfName() {
		return mfName;
	}
	public void setMfName(String mfName) {
		this.mfName = mfName;
	}
	public double getCashBalance() {
		return cashBalance;
	}
	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}
	public double getEntryLoad() {
		return entryLoad;
	}
	public void setEntryLoad(double entryLoad) {
		this.entryLoad = entryLoad;
	}
	public double getExitLoad() {
		return exitLoad;
	}
	public void setExitLoad(double exitLoad) {
		this.exitLoad = exitLoad;
	}
	public double getExpenseRatio() {
		return expenseRatio;
	}
	public void setExpenseRatio(double expenseRatio) {
		this.expenseRatio = expenseRatio;
	}
	public List<PortfolioJson> getPorfolio() {
		return porfolio;
	}
	public void setPorfolio(List<PortfolioJson> porfolio) {
		this.porfolio = porfolio;
	}


	@Override
	public String toString() {
		return "MutualFundJson [mfName=" + mfName + ", cashBalance=" + cashBalance + ", entryLoad=" + entryLoad
				+ ", exitLoad=" + exitLoad + ", expenseRatio=" + expenseRatio + ", porfolio=" + porfolio + "]";
	}
	
	//to_string
	
	
}