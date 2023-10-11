package com.main.model.investor;

import javax.persistence.*;

@Entity
@Table(name = "Investerportfolio")
@IdClass(InvestorPortfolioId.class)
public class InvestorPortfolio {

    @Id
    private int investorId;

    @Id
    private int mfId;

    @Column//(name = "units", nullable = false)
    private double units;

    @Column//(name = "amount_invested", precision = 14, scale = 2, nullable = false)
    private double amountInvested;


    public InvestorPortfolio() {
        super();
    }

	public InvestorPortfolio(int investorId, int mfId, double units, double amountInvested) {
		super();
		this.investorId = investorId;
		this.mfId = mfId;
		this.units = units;
		this.amountInvested = amountInvested;
	}

	public int getInvestorId() {
		return investorId;
	}

	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}

	public int getMfId() {
		return mfId;
	}

	public void setMfId(int mfId) {
		this.mfId = mfId;
	}

	public double getUnits() {
		return units;
	}

	public void setUnits(double units) {
		this.units = units;
	}

	public double getAmountInvested() {
		return amountInvested;
	}

	public void setAmountInvested(double amountInvested) {
		this.amountInvested = amountInvested;
	}
    
    
    
}
