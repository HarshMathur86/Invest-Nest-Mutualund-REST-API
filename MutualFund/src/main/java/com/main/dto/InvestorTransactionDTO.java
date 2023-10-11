package com.main.dto;

import java.util.Date;

public class InvestorTransactionDTO {
	private String investorName;
	private String mfName;
	private String transactionType;
	private double transactionAmount;
	private Date transactionDate;
	
	
	public InvestorTransactionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvestorTransactionDTO(String investorName, String mfName, String transactionType, double transactionAmount,
			Date transactionDate) {
		super();
		this.investorName = investorName;
		this.mfName = mfName;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public String getMfName() {
		return mfName;
	}

	public void setMfName(String mfName) {
		this.mfName = mfName;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
}
